package com.libraryreact.libraryspringboot.controllers.dataMasterController;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.KategoriDto;
import com.libraryreact.libraryspringboot.service.dataMasterService.KategoriService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kategori")
@CrossOrigin(origins = "http://localhost:3000")
public class KategoriController {
    @Autowired
    private KategoriService katService;

    // Insert new kategori
    @PostMapping("/add")
    public ResponseEntity<?> addKategori(@RequestBody KategoriDto dto){
        try {
            StatusMessageDto<KategoriDto> response = new StatusMessageDto<>();
            if(dto.getKodeKategori().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kode kategori kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }
            if(dto.getNamaKategori().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Nama kategori kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }

            KategoriDto KategoriDto = katService.create(dto);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Kategori berhasil ditambahkan!");
            response.setData(KategoriDto);
            return ResponseEntity.ok().body(response);
   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get all kategori without is deleted status
    @GetMapping("/all")
    public ResponseEntity<?> getAllKategori(){
        try {
            StatusMessageDto<List<KategoriDto>> response = new StatusMessageDto<>();
            List<KategoriDto> KategoriDtos = katService.getAll();
            if(KategoriDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar kategori kosong!");
                response.setData(KategoriDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar kategori ditemukan!");
                response.setData(KategoriDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by id kategori without is deleted status
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByIdKategori(@PathVariable Integer id){
        try {
            StatusMessageDto<KategoriDto> response = new StatusMessageDto<>();
            KategoriDto KategoriDto = katService.getById(id);
            if(KategoriDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kategori tidak ditemukan!");
                response.setData(KategoriDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kategori ditemukan!");
                response.setData(KategoriDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by kode kategori without is deleted status
    @GetMapping("/kode/{kodekategori}")
    public ResponseEntity<?> getByKodeKategori(@PathVariable String kodekategori){
        try {
            StatusMessageDto<KategoriDto> response = new StatusMessageDto<>();
            KategoriDto KategoriDto = katService.getByKode(kodekategori);
            if(KategoriDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kategori tidak ditemukan!");
                response.setData(KategoriDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kategori ditemukan!");
                response.setData(KategoriDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by nama kategori without is deleted status
    @GetMapping("/nama/{namaKategori}")
    public ResponseEntity<?> getByNamaKategori(@PathVariable String namaKategori){
        try {
            StatusMessageDto<KategoriDto> response = new StatusMessageDto<>();
            KategoriDto KategoriDto = katService.getByNama(namaKategori);
            if(KategoriDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kategori tidak ditemukan!");
                response.setData(KategoriDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kategori ditemukan!");
                response.setData(KategoriDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Update kategori by id and show result without is deleted status
    @PutMapping("/edit")
    public ResponseEntity<?> editKategori(@RequestBody KategoriDto dto){
        try {
            StatusMessageDto<KategoriDto> response = new StatusMessageDto<>();
            if(dto.getKodeKategori().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kode kategori kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }
            if(dto.getNamaKategori().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Nama kategori kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }
            
            KategoriDto kategoriDto = katService.update(dto);
            if(kategoriDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kategori gagal diubah!");
                response.setData(kategoriDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kategori berhasil diubah!");
                response.setData(kategoriDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Remove kategori by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removeKategori(@PathVariable Integer id){
        try {
            StatusMessageDto<KategoriDto> response = new StatusMessageDto<>();
            KategoriDto KategoriDto = katService.delete(id);
            if(KategoriDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Kategori gagal dihapus!");
                response.setData(KategoriDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Kategori berhasil dihapus!");
                response.setData(KategoriDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
