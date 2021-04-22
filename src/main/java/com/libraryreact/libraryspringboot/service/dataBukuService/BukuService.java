package com.libraryreact.libraryspringboot.service.dataBukuService;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.BukuDto;

public interface BukuService {
    public BukuDto create(BukuDto dto);
    public List<BukuDto> getAll();
    public BukuDto getById(Integer id);
    public BukuDto update(BukuDto dto);
    public BukuDto delete(Integer id);
}
