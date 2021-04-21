package com.libraryreact.libraryspringboot.service.dataMasterService;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.LokasiDto;

public interface LokasiService {
    public LokasiDto create(LokasiDto dto);
    public List<LokasiDto> getAll();
    public LokasiDto getById(Integer id);
    public LokasiDto getByKode(String kodeLokasi);
    public LokasiDto update(LokasiDto dto);
    public LokasiDto delete(Integer id);
}
