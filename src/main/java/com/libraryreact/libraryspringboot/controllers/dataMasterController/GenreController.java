package com.libraryreact.libraryspringboot.controllers.dataMasterController;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.GenreDto;
import com.libraryreact.libraryspringboot.service.dataMasterService.GenreService;

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
@RequestMapping("api/v1/genre")
@CrossOrigin(origins = "http://localhost:3000")
public class GenreController {
    @Autowired
    private GenreService genreService;

    // Insert new genre
    @PostMapping("/add")
    public ResponseEntity<?> addGenre(@RequestBody GenreDto dto){
        try {
            StatusMessageDto<GenreDto> response = new StatusMessageDto<>();
            if(dto.getNamaGenre().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Genre kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }

            GenreDto genreDto = genreService.create(dto);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Genre berhasil ditambahkan!");
            response.setData(genreDto);
            return ResponseEntity.ok().body(response);
   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get all genre without is deleted status
    @GetMapping("/all")
    public ResponseEntity<?> getAllGenre(){
        try {
            StatusMessageDto<List<GenreDto>> response = new StatusMessageDto<>();
            List<GenreDto> genreDtos = genreService.getAll();
            if(genreDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar genre kosong!");
                response.setData(genreDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar genre ditemukan!");
                response.setData(genreDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by id genre without is deleted status
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByIdGenre(@PathVariable Integer id){
        try {
            StatusMessageDto<GenreDto> response = new StatusMessageDto<>();
            GenreDto genreDto = genreService.getById(id);
            if(genreDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Genre tidak ditemukan!");
                response.setData(genreDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Genre ditemukan!");
                response.setData(genreDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Get by id genre without is deleted status
    @GetMapping("/name/{namaGenre}")
    public ResponseEntity<?> getByNamaGenre(@PathVariable String namaGenre){
        try {
            StatusMessageDto<GenreDto> response = new StatusMessageDto<>();
            GenreDto genreDto = genreService.getByNama(namaGenre);
            if(genreDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Genre tidak ditemukan!");
                response.setData(genreDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Genre ditemukan!");
                response.setData(genreDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Update genre by id and show result without is deleted status
    @PutMapping("/edit")
    public ResponseEntity<?> editGenre(@RequestBody GenreDto dto){
        try {
            StatusMessageDto<GenreDto> response = new StatusMessageDto<>();
            if(dto.getNamaGenre().isEmpty() == true){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Genre kosong!");
                response.setData(dto);
                return ResponseEntity.badRequest().body(response);
            }
            
            GenreDto genreDto = genreService.update(dto);
            if(genreDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Genre gagal diubah!");
                response.setData(genreDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Genre berhasil diubah!");
                response.setData(genreDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // Remove genre by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removeGenre(@PathVariable Integer id){
        try {
            StatusMessageDto<GenreDto> response = new StatusMessageDto<>();
            GenreDto genreDto = genreService.delete(id);
            if(genreDto == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Genre gagal dihapus!");
                response.setData(genreDto);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Genre berhasil dihapus!");
                response.setData(genreDto);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
