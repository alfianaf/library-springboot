package com.libraryreact.libraryspringboot.controllers.dataMasterController;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.LokasiDto;
import com.libraryreact.libraryspringboot.service.dataMasterService.LokasiService;

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
@RequestMapping("/api/v1/lokasi")
public class LokasiController {
    @Autowired
    private LokasiService lokService;

    // Insert new Lokasi
    @PostMapping("/add")
    public ResponseEntity<?> addLokasi(@RequestBody LokasiDto dto){
        try {
            StatusMessageDto<LokasiDto> response = new StatusMessageDto<>();
            if(dto.getKodeLokasi().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kode lokasi kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }
            if(dto.getKeteranganLokasi().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Keterangan lokasi kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }

            LokasiDto lokasiDto = lokService.create(dto);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Lokasi berhasil ditambahkan!");
            response.setData(lokasiDto);
            return ResponseEntity.badRequest().body(response);
   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get all Lokasi without is deleted status
    @GetMapping("/all")
    public ResponseEntity<?> getAllLokasi(){
        try {
            StatusMessageDto<List<LokasiDto>> response = new StatusMessageDto<>();
            List<LokasiDto> lokasiDtos = lokService.getAll();
            if(lokasiDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar Lokasi kosong!");
                response.setData(lokasiDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar Lokasi ditemukan!");
                response.setData(lokasiDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by id Lokasi without is deleted status
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByIdLokasi(@PathVariable Integer id){
        try {
            StatusMessageDto<LokasiDto> response = new StatusMessageDto<>();
            LokasiDto lokasiDto = lokService.getById(id);
            if(lokasiDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Lokasi tidak ditemukan!");
                response.setData(lokasiDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Lokasi ditemukan!");
                response.setData(lokasiDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by id Lokasi without is deleted status
    @GetMapping("/kode/{kodeLokasi}")
    public ResponseEntity<?> getByKodeLokasi(@PathVariable String kodeLokasi){
        try {
            StatusMessageDto<LokasiDto> response = new StatusMessageDto<>();
            LokasiDto lokasiDto = lokService.getByKode(kodeLokasi);
            if(lokasiDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Lokasi tidak ditemukan!");
                response.setData(lokasiDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Lokasi ditemukan!");
                response.setData(lokasiDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Update Lokasi by id and show result without is deleted status
    @PutMapping("/edit")
    public ResponseEntity<?> editLokasi(@RequestBody LokasiDto dto){
        try {
            StatusMessageDto<LokasiDto> response = new StatusMessageDto<>();
            if(dto.getKodeLokasi().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kode lokasi kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }
            if(dto.getKeteranganLokasi().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Keterangan lokasi kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }
            
            LokasiDto lokasiDto = lokService.update(dto);
            if(lokasiDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Lokasi gagal diubah!");
                response.setData(lokasiDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Lokasi berhasil diubah!");
                response.setData(lokasiDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Remove Lokasi by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removeLokasi(@PathVariable Integer id){
        try {
            StatusMessageDto<LokasiDto> response = new StatusMessageDto<>();
            LokasiDto lokasiDto = lokService.delete(id);
            if(lokasiDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Lokasi gagal dihapus!");
                response.setData(lokasiDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Lokasi berhasil dihapus!");
                response.setData(lokasiDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
