package com.libraryreact.libraryspringboot.service;

import com.libraryreact.libraryspringboot.models.dto.UsersDto;
import com.libraryreact.libraryspringboot.models.entity.Users;

import org.springframework.http.ResponseEntity;

public interface UsersService {
    public ResponseEntity<?> regist(UsersDto usersDto);

    public ResponseEntity<?> edit(Integer id, UsersDto usersDto);

    public ResponseEntity<?> delete(Integer id);

    public Users register(Users user);

    public Users login(String username, String password);
}
