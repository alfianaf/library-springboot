package com.libraryreact.libraryspringboot.service.dataBukuService;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.BukuUsersDto;

public interface BukuUsersService {
    public List<BukuUsersDto> getAllCollections();
    public List<BukuUsersDto> getNewCollections();
    public List<BukuUsersDto> getHotCollections();
}
