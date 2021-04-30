package com.libraryreact.libraryspringboot;

import com.libraryreact.libraryspringboot.property.FileStorageProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = FileStorageProperties.class)
public class LibrarySpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySpringbootApplication.class, args);
	}

}
