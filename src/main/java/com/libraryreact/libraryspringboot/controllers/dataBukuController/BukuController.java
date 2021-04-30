package com.libraryreact.libraryspringboot.controllers.dataBukuController;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.BukuDto;
import com.libraryreact.libraryspringboot.service.dataBukuService.BukuService;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/buku")
@CrossOrigin(origins = "http://localhost:3000")

public class BukuController {
    @Autowired
    private BukuService bukuService;

    // Insert new buku
    @PostMapping("/add")
    public ResponseEntity<?> addBuku(@RequestBody BukuDto dto) {
        try {
            StatusMessageDto<BukuDto> response = new StatusMessageDto<>();
            // if(dto.getNamaBuku().isEmpty() == true){
            // response.setStatus(HttpStatus.BAD_REQUEST.value());
            // response.setMessage("buku kosong!");
            // response.setData(dto);
            // return ResponseEntity.badRequest().body(response);
            // }

            BukuDto bukuDto = bukuService.create(dto);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("buku berhasil ditambahkan!");
            response.setData(bukuDto);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get all buku without is deleted status
    @GetMapping("/all")
    public ResponseEntity<?> getAllBuku() {
        try {
            StatusMessageDto<List<BukuDto>> response = new StatusMessageDto<>();
            List<BukuDto> BukuDtos = bukuService.getAll();
            if (BukuDtos.size() == 0) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar buku kosong!");
                response.setData(BukuDtos);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar buku ditemukan!");
                response.setData(BukuDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by id buku without is deleted status
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByIdBuku(@PathVariable Integer id) {
        try {
            StatusMessageDto<BukuDto> response = new StatusMessageDto<>();
            BukuDto bukuDto = bukuService.getById(id);
            if (bukuDto == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("buku tidak ditemukan!");
                response.setData(bukuDto);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("buku ditemukan!");
                response.setData(bukuDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Update buku by id and show result without is deleted status
    @PutMapping("/edit")
    public ResponseEntity<?> editBuku(@RequestBody BukuDto dto) {
        try {
            StatusMessageDto<BukuDto> response = new StatusMessageDto<>();
            // if(dto.getNamaBuku().isEmpty() == true){
            // response.setStatus(HttpStatus.BAD_REQUEST.value());
            // response.setMessage("buku kosong!");
            // response.setData(dto);
            // return ResponseEntity.badRequest().body(response);
            // }

            BukuDto bukuDto = bukuService.update(dto);
            if (bukuDto == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("buku gagal diubah!");
                response.setData(bukuDto);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("buku berhasil diubah!");
                response.setData(bukuDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Remove buku by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removeBuku(@PathVariable Integer id) {
        try {
            StatusMessageDto<BukuDto> response = new StatusMessageDto<>();
            BukuDto bukuDto = bukuService.delete(id);
            if (bukuDto == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("buku gagal dihapus!");
                response.setData(bukuDto);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("buku berhasil dihapus!");
                response.setData(bukuDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
