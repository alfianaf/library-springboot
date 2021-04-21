package com.libraryreact.libraryspringboot.controllers.dataMasterController;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.PenerbitDto;
import com.libraryreact.libraryspringboot.service.dataMasterService.PenerbitService;

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
@RequestMapping("api/v1/penerbit")
public class PenerbitController {
    @Autowired
    private PenerbitService penerbitService;

    // Insert new Penerbit
    @PostMapping("/add")
    public ResponseEntity<?> addPenerbit(@RequestBody PenerbitDto dto){
        try {
            StatusMessageDto<PenerbitDto> response = new StatusMessageDto<>();
            if(dto.getNamaPenerbit().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Penerbit kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }

            PenerbitDto penerbitDto = penerbitService.create(dto);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Penerbit berhasil ditambahkan!");
            response.setData(penerbitDto);
            return ResponseEntity.badRequest().body(response);
   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get all Penerbit without is deleted status
    @GetMapping("/all")
    public ResponseEntity<?> getAllPenerbit(){
        try {
            StatusMessageDto<List<PenerbitDto>> response = new StatusMessageDto<>();
            List<PenerbitDto> penerbitDtos = penerbitService.getAll();
            if(penerbitDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar Penerbit kosong!");
                response.setData(penerbitDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar Penerbit ditemukan!");
                response.setData(penerbitDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by id Penerbit without is deleted status
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByIdPenerbit(@PathVariable Integer id){
        try {
            StatusMessageDto<PenerbitDto> response = new StatusMessageDto<>();
            PenerbitDto penerbitDto = penerbitService.getById(id);
            if(penerbitDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Penerbit tidak ditemukan!");
                response.setData(penerbitDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Penerbit ditemukan!");
                response.setData(penerbitDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by id Penerbit without is deleted status
    @GetMapping("/name/{namaPenerbit}")
    public ResponseEntity<?> getByNamaPenerbit(@PathVariable String namaPenerbit){
        try {
            StatusMessageDto<PenerbitDto> response = new StatusMessageDto<>();
            PenerbitDto penerbitDto = penerbitService.getByNama(namaPenerbit);
            if(penerbitDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Penerbit tidak ditemukan!");
                response.setData(penerbitDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Penerbit ditemukan!");
                response.setData(penerbitDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Update Penerbit by id and show result without is deleted status
    @PutMapping("/edit")
    public ResponseEntity<?> editPenerbit(@RequestBody PenerbitDto dto){
        try {
            StatusMessageDto<PenerbitDto> response = new StatusMessageDto<>();
            if(dto.getNamaPenerbit().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Penerbit kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }
            
            PenerbitDto penerbitDto = penerbitService.update(dto);
            if(penerbitDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Penerbit gagal diubah!");
                response.setData(penerbitDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Penerbit berhasil diubah!");
                response.setData(penerbitDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Remove Penerbit by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removePenerbit(@PathVariable Integer id){
        try {
            StatusMessageDto<PenerbitDto> response = new StatusMessageDto<>();
            PenerbitDto penerbitDto = penerbitService.delete(id);
            if(penerbitDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Penerbit gagal dihapus!");
                response.setData(penerbitDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Penerbit berhasil dihapus!");
                response.setData(penerbitDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
