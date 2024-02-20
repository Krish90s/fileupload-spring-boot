package com.webuploader.fileupload.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary("");
	}
}
