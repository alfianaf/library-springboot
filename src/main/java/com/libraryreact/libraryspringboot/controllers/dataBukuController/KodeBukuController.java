package com.libraryreact.libraryspringboot.controllers.dataBukuController;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.KodeBukuDto;
import com.libraryreact.libraryspringboot.service.dataBukuService.KodeBukuService;
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
@RequestMapping("api/v1/kodebuku")
@CrossOrigin(origins = "http://localhost:3000")

public class KodeBukuController {
    @Autowired
    private KodeBukuService kodeService;

    // Insert new buku
    @PostMapping("/add")
    public ResponseEntity<?> addBuku(@RequestBody KodeBukuDto dto) {
        try {
            StatusMessageDto<KodeBukuDto> response = new StatusMessageDto<>();
            // if(dto.getNamaBuku().isEmpty() == true){
            // response.setStatus(HttpStatus.BAD_REQUEST.value());
            // response.setMessage("Kode buku kosong!");
            // response.setData(dto);
            // return ResponseEntity.badRequest().body(response);
            // }

            KodeBukuDto kodeBukuDto = kodeService.create(dto);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Kode buku berhasil ditambahkan!");
            response.setData(kodeBukuDto);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get all buku available without is deleted status
    @GetMapping("/all-available")
    public ResponseEntity<?> getAllAvailable() {
        try {
            StatusMessageDto<List<KodeBukuDto>> response = new StatusMessageDto<>();
            List<KodeBukuDto> BukuDtos = kodeService.getAllAvailable();
            if (BukuDtos.size() == 0) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar kode buku kosong!");
                response.setData(BukuDtos);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar kode buku ditemukan!");
                response.setData(BukuDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get all buku without is deleted status
    @GetMapping("/all")
    public ResponseEntity<?> getAllBuku() {
        try {
            StatusMessageDto<List<KodeBukuDto>> response = new StatusMessageDto<>();
            List<KodeBukuDto> BukuDtos = kodeService.getAll();
            if (BukuDtos.size() == 0) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar kode buku kosong!");
                response.setData(BukuDtos);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar kode buku ditemukan!");
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
            StatusMessageDto<KodeBukuDto> response = new StatusMessageDto<>();
            KodeBukuDto kodeBukuDto = kodeService.getById(id);
            if (kodeBukuDto == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kode buku tidak ditemukan!");
                response.setData(kodeBukuDto);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kode buku ditemukan!");
                response.setData(kodeBukuDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by kode buku without is deleted status
    @GetMapping("/kode/{kodeBuku}")
    public ResponseEntity<?> getByKodeBuku(@PathVariable String kodeBuku) {
        try {
            StatusMessageDto<KodeBukuDto> response = new StatusMessageDto<>();
            KodeBukuDto kodeBukuDto = kodeService.getByKode(kodeBuku);
            if (kodeBukuDto == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kode buku tidak ditemukan!");
                response.setData(kodeBukuDto);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kode buku ditemukan!");
                response.setData(kodeBukuDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Update buku by id and show result without is deleted status
    @PutMapping("/edit")
    public ResponseEntity<?> editBuku(@RequestBody KodeBukuDto dto) {
        try {
            StatusMessageDto<KodeBukuDto> response = new StatusMessageDto<>();
            // if(dto.getNamaBuku().isEmpty() == true){
            // response.setStatus(HttpStatus.BAD_REQUEST.value());
            // response.setMessage("Kode buku kosong!");
            // response.setData(dto);
            // return ResponseEntity.badRequest().body(response);
            // }

            KodeBukuDto kodeBukuDto = kodeService.update(dto);
            if (kodeBukuDto == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kode buku gagal diubah!");
                response.setData(kodeBukuDto);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kode buku berhasil diubah!");
                response.setData(kodeBukuDto);
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
            StatusMessageDto<KodeBukuDto> response = new StatusMessageDto<>();
            KodeBukuDto kodeBukuDto = kodeService.delete(id);
            if (kodeBukuDto == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kode buku gagal dihapus!");
                response.setData(kodeBukuDto);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kode buku berhasil dihapus!");
                response.setData(kodeBukuDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
