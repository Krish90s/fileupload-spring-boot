package com.webuploader.fileupload.service;

import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.webuploader.fileupload.model.FileMetaData;
import com.webuploader.fileupload.repository.FileMetadataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloudinary.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FileUploadService {
	
	private final FileMetadataRepository fileMetadataRepository;
	@Autowired
	private final Cloudinary cloudinary;
	
	public void uploadFiles(MultipartFile[] files) {
		try{
			
			FileMetaData fileMetaData = new FileMetaData();
			
			
			for (MultipartFile file: files) {
				
				String filepath = uploadToCloudinary(file);
				fileMetaData.setFileLocation(filepath);
				fileMetaData.setFileName(file.getOriginalFilename());
				fileMetaData.setFileType(file.getContentType());
				fileMetaData.setFileSize(file.getSize());
				fileMetadataRepository.save(fileMetaData);
				
			}
			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Failed to upload file: " + e.getMessage());
		}
	}
	
	public ResponseEntity<List<FileMetaData>> getAllFiles(){
		List<FileMetaData> fileMetaDataList = fileMetadataRepository.findAll();
		System.out.println(fileMetaDataList);
		return ResponseEntity.ok(fileMetaDataList);
	}
	
	
	public ResponseEntity<String> deleteFile(String fileId) throws Exception {
		
		FileMetaData file = fileMetadataRepository.findById(fileId).orElse(null);
		
		if(file != null){
		
			var res = cloudinary.api().deleteResources(List.of("test/" + file.getFileName()),
					ObjectUtils.asMap("type", "upload", "resource_type", "image"));
			System.out.println(res);
			fileMetadataRepository.deleteById(fileId);
			return ResponseEntity.ok("file deleted successfully");
		}
		
		return ResponseEntity.ok("file not found");
	}
	
	public String uploadToCloudinary(MultipartFile file) throws IOException {
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), // Upload byte array instead of input stream
				ObjectUtils.asMap("folder", "test", "public_id", file.getOriginalFilename()));
		return (String) uploadResult.get("secure_url");
	}
	
	
}
