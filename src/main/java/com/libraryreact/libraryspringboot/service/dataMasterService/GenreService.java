package com.libraryreact.libraryspringboot.service.dataMasterService;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.GenreDto;

public interface GenreService {
    public GenreDto create(GenreDto dto);
    public List<GenreDto> getAll();
    public GenreDto getById(Integer id);
    public GenreDto getByNama(String namaGenre);
    public GenreDto update(GenreDto dto);
    public GenreDto delete(Integer id);
}
