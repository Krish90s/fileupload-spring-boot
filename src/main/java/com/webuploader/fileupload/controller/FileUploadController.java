package com.webuploader.fileupload.controller;

import com.webuploader.fileupload.model.FileMetaData;
import com.webuploader.fileupload.service.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1/files")
@AllArgsConstructor
public class FileUploadController {
	
	private final FileUploadService fileUploadService;

	
	@PostMapping
	public void uploadFile(@RequestParam("file") MultipartFile[] files){
		fileUploadService.uploadFiles(files);
	}
	
	@GetMapping
	public ResponseEntity<List<FileMetaData>> getFiles(){
		return fileUploadService.getAllFiles();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFile(@PathVariable("id") String id) throws Exception {
		return fileUploadService.deleteFile(id);
	}
	
}
