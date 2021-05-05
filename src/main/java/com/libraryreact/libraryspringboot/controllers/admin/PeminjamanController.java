package com.libraryreact.libraryspringboot.controllers.admin;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.PeminjamanDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.PopulerDto;
import com.libraryreact.libraryspringboot.service.PeminjamanService;
import com.libraryreact.libraryspringboot.service.dataBukuService.BukuUsersService;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins = "http://localhost:3000")
public class PeminjamanController {
    @Autowired
    private PeminjamanService peminjamanService;
    @Autowired
    private BukuUsersService bukuService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            return peminjamanService.getAll();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // get terpopuler
    @GetMapping("/buku/terpopuler")
    public ResponseEntity<?> getHotCollections(){
        try {
            StatusMessageDto<List<PopulerDto>> response = new StatusMessageDto<>();
            List<PopulerDto> populerDtos = bukuService.getHotCollections2();
            
            if(populerDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar buku kosong!");
                response.setData(populerDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar buku ditemukan!");
                response.setData(populerDtos);
                return ResponseEntity.ok().body(response);
            }
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
            if (response.getStatus().equals(400)) {
                return ResponseEntity.badRequest().body(response);
            }
            if (response.getStatus().equals(410)) {
                return ResponseEntity.status(410).body(response);
            }
            if (response.getStatus().equals(404)) {
                return ResponseEntity.status(404).body(response);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
