package com.libraryreact.libraryspringboot.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.libraryreact.libraryspringboot.models.dto.FileInfo;
import com.libraryreact.libraryspringboot.models.entity.FilesEntity;
import com.libraryreact.libraryspringboot.repository.FilesRepository;
import com.libraryreact.libraryspringboot.service.FileStorageServiceImpl;
import com.libraryreact.libraryspringboot.service.PhotoStorageServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin(origins = "http://localhost:3000")
public class FilesController {
    private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

    @Autowired
    private FileStorageServiceImpl fileStorageService;
    @Autowired
    private PhotoStorageServiceImp photoStorageService;
    @Autowired
    private FilesRepository fileRepo;

    @PostMapping("/uploadsampul")
    public FileInfo uploadFile(@RequestParam("file") MultipartFile file) {
        String filename = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/files/download/")
                .path(filename).toUriString();

        return new FileInfo(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadfoto")
    public FileInfo uploadFile2(@RequestParam("file") MultipartFile file) {
        String filename = photoStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/files/download/")
                .path(filename).toUriString();

        return new FileInfo(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/uploads")
    public List<FileInfo> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        List<FileInfo> responseFiles = Arrays.asList(files).stream().map(file -> uploadFile(file))
                .collect(Collectors.toList());

        return responseFiles;
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(filename);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            // TODO: handle exception
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/downloadprofil/{filename:.+}")
    public ResponseEntity<Resource> getFile2(@PathVariable String filename, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = photoStorageService.loadFileAsResource(filename);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            // TODO: handle exception
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<FilesEntity> files = fileRepo.findAll();

        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/{id}")
    public FilesEntity getById(@PathVariable Integer id) {
        FilesEntity filesEntity = fileRepo.findById(id).get();
        return filesEntity;
    }
}
