package com.libraryreact.libraryspringboot.controllers.admin;

import com.libraryreact.libraryspringboot.models.dto.PeminjamanDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.service.PeminjamanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/admin")
public class PeminjamanController {
    @Autowired
    private PeminjamanService peminjamanService;

    // Sewa Buku
    @PostMapping("/sewa")
    public ResponseEntity<?> sewa(@RequestBody PeminjamanDto dto){
        try {
            StatusMessageDto<PeminjamanDto> response = peminjamanService.sewaBuku(dto);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
