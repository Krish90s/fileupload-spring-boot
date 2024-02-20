package com.webuploader.fileupload.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "files")
public class FileMetaData {
	
	@Id
	private String id;
	private String fileName;
	private String fileType;
	private long fileSize;
	private String fileLocation;
	
}
