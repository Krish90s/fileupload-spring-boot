package com.webuploader.fileupload.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary("cloudinary://167232775669322:3YYbteBCvEm53cecaC4tZI6EG5U@djw9idhnp");
	}
}
