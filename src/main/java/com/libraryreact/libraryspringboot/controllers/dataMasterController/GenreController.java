package com.libraryreact.libraryspringboot.controllers.dataMasterController;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.GenreDto;
import com.libraryreact.libraryspringboot.service.dataMasterService.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    // Insert new genre
    @PostMapping("/add")
    public ResponseEntity<?> addGenre(GenreDto dto){
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
            return ResponseEntity.badRequest().body(response);
   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
