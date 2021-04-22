package com.libraryreact.libraryspringboot.controllers.dataBukuController;

import java.util.ArrayList;
import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.BukuUsersDto;
import com.libraryreact.libraryspringboot.service.dataBukuService.BukuUsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/buku")
public class BukuUsersController {
    @Autowired
    private BukuUsersService bukuService;

    // get all book for user
    @GetMapping("/all")
    public ResponseEntity<?> getAllCollections(){
        try {
            StatusMessageDto<List<BukuUsersDto>> response = new StatusMessageDto<>();
            List<BukuUsersDto> BukuDtos = bukuService.getAllCollections();
            
            if(BukuDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar buku kosong!");
                response.setData(BukuDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar buku ditemukan!");
                response.setData(BukuDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
