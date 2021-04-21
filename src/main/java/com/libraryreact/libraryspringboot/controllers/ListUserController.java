package com.libraryreact.libraryspringboot.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.UsersDto;

import com.libraryreact.libraryspringboot.models.entity.UserDetail;
import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.repository.UserDetailRepository;
import com.libraryreact.libraryspringboot.repository.UsersRepository;
import com.libraryreact.libraryspringboot.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RestController
@RequestMapping("/user")
public class ListUserController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private UsersService usersService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        List<Users> user = usersRepository.findAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-detail/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Users user = usersRepository.findById(id).get();
        UserDetail userDetail = userDetailRepository.findDetailByUserId(user.getId());

        return ResponseEntity.ok(userDetail);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody UsersDto usersDto) {
        StatusMessageDto<Users> result = new StatusMessageDto<>();

        try {
            return usersService.edit(id, usersDto);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/tambahsaldo/{id}")
    public ResponseEntity<?> tambahSaldo(@PathVariable Integer id, @RequestBody UsersDto usersDto) {
        StatusMessageDto<Users> result = new StatusMessageDto<>();

        try {
            return usersService.tambahSaldo(id, usersDto);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return usersService.delete(id);
    }
}
