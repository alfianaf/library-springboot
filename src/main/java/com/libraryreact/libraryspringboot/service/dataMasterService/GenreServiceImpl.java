package com.libraryreact.libraryspringboot.service.dataMasterService;

import java.util.ArrayList;
import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.GenreDto;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Genre;
import com.libraryreact.libraryspringboot.repository.dataMasterRepository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenreServiceImpl implements GenreService{
    @Autowired
    private GenreRepository genreRepo;

    // Create Genre Entity
    @Override
    public GenreDto create(GenreDto dto) {
        Genre genre = new Genre();

        genre.setNamaGenre(dto.getNamaGenre());
        genre.setDeleted(false);
        
        genreRepo.save(genre);

        return new GenreDto(genre.getId(), genre.getNamaGenre());
    }

    // Get All Genre (return without is deleted status)
    @Override
    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepo.findByIsDeleted(false);
        List<GenreDto> genreDtos = new ArrayList<>();
        for (Genre genre : genres) {
            genreDtos.add(new GenreDto(genre.getId(), genre.getNamaGenre()));
        }
        return genreDtos;
    }

    // Get genre by id (return without is deleted status)
    @Override
    public GenreDto getById(Integer id) {
        Genre genre = genreRepo.findByIdGenre(id);
        if(genre == null){
            return null;
        }
        else{
            return new GenreDto(genre.getId(), genre.getNamaGenre());
        }
    }

    // Get genre by name (return without is deleted status)
    @Override
    public GenreDto getByNama(String namaGenre) {
        Genre genre = genreRepo.findByNamaGenre(namaGenre);
        if(genre == null){
            return null;
        }
        else{
            return new GenreDto(genre.getId(), genre.getNamaGenre());
        }
    }

    // Update name of genre (return without is deleted status)
    @Override
    public GenreDto update(GenreDto dto) {
        Genre genre = genreRepo.findByIdGenre(dto.getId());
        if(genre != null){
            genre.setNamaGenre(dto.getNamaGenre());
            genreRepo.save(genre);
            return new GenreDto(dto.getId(), genre.getNamaGenre());
        }
        else{
            return null;
        }
    }

    // Soft deleted and return without is deleted status
    @Override
    public GenreDto delete(Integer id) {
        Genre genre = genreRepo.findByIdGenre(id);
        if(genre != null){
            genre.setDeleted(true);
            genreRepo.save(genre);
            return new GenreDto(genre.getId(), genre.getNamaGenre());
        }
        else{
            return null;
        }
    }
    
}
