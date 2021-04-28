package com.libraryreact.libraryspringboot.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.libraryreact.libraryspringboot.models.dto.SaldoDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.UsersDto;

import com.libraryreact.libraryspringboot.models.entity.UserDetail;
import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.repository.UserDetailRepository;
import com.libraryreact.libraryspringboot.repository.UsersRepository;
import com.libraryreact.libraryspringboot.service.SaldoLogService;
import com.libraryreact.libraryspringboot.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class ListUserController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private SaldoLogService saldoLogService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            StatusMessageDto<List<Users>> response = new StatusMessageDto<>();
            List<Users> users = usersRepository.findByIsActive(true);
            if (users.size() == 0) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("User Tidak ditemukan!");
                response.setData(users);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("User ditemukan!");
                response.setData(users);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/get-detail/{id}")
    public UserDetail getDetail(@PathVariable Integer id) {
        Users user = usersRepository.findActiveById(id);
        UserDetail userDetail = userDetailRepository.findDetailByUserId(user.getId());

        return userDetail;
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
    public ResponseEntity<?> tambahSaldo(@PathVariable Integer id, @RequestBody SaldoDto saldoDto) {
        StatusMessageDto<Users> result = new StatusMessageDto<>();

        try {
            return saldoLogService.addSaldo(id, saldoDto);
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
