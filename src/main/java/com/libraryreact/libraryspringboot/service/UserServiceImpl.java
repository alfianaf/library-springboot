package com.libraryreact.libraryspringboot.service;

import com.libraryreact.libraryspringboot.models.dto.UsersDto;
import com.libraryreact.libraryspringboot.models.entity.Users;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UsersService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> regist(UsersDto usersDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> edit(Integer id, UsersDto usersDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users register(Users user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users login(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

}
