package com.libraryreact.libraryspringboot.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.libraryreact.libraryspringboot.exception.FileStorageException;
import com.libraryreact.libraryspringboot.exception.MyFileNotFoundException;
import com.libraryreact.libraryspringboot.models.entity.FilesEntity;
import com.libraryreact.libraryspringboot.property.FileStorageProperties;
import com.libraryreact.libraryspringboot.repository.FilesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoStorageServiceImp implements FileStorageService {
    private Path fileStorageLocation;

    @Autowired
    private FilesRepository repo;

    @Autowired
    public void FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getPhotoDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            // TODO: handle exception
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
                    e);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        // TODO Auto-generated method stub
        // Normalize file name
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (filename.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Save file info to db
            FilesEntity filesEntity = new FilesEntity(filename, file.getContentType());
            repo.save(filesEntity);

            return filename;
        } catch (IOException e) {
            // TODO: handle exception
            throw new FileStorageException("Could not store file " + filename + ". Please try again!", e);
        }
    }

    @Override
    public Resource loadFileAsResource(String filename) {
        // TODO Auto-generated method stub
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + filename);
            }
        } catch (MalformedURLException e) {
            // TODO: handle exception
            throw new MyFileNotFoundException("File not found " + filename, e);
        }
    }

}
