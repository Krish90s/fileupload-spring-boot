package com.webuploader.fileupload.repository;

import com.webuploader.fileupload.model.FileMetaData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileMetadataRepository extends MongoRepository<FileMetaData, String> {
}
