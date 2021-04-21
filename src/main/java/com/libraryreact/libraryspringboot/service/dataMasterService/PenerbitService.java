package com.libraryreact.libraryspringboot.service.dataMasterService;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.PenerbitDto;

public interface PenerbitService {
    public PenerbitDto create(PenerbitDto dto);
    public List<PenerbitDto> getAll();
    public PenerbitDto getById(Integer id);
    public PenerbitDto getByNama(String namaPenerbit);
    public PenerbitDto update(PenerbitDto dto);
    public PenerbitDto delete(Integer id);
}
