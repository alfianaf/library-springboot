package com.libraryreact.libraryspringboot.controllers.admin;

import com.libraryreact.libraryspringboot.models.dto.PeminjamanDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.service.PeminjamanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/admin/peminjaman")
public class PeminjamanController {
    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            return peminjamanService.getAll();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getDetailById(@PathVariable Integer id) {
        try {
            return peminjamanService.getDetailById(id);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Sewa Buku
    @PostMapping("/pengembalian")
    public ResponseEntity<?> pengembalian(@RequestBody PeminjamanDto dto) {
        try {
            return peminjamanService.pengembalianBuku(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Sewa Buku
    @PostMapping("/sewa")
    public ResponseEntity<?> sewa(@RequestBody PeminjamanDto dto) {
        try {
            StatusMessageDto<PeminjamanDto> response = peminjamanService.sewaBuku(dto);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
