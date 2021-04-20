package com.libraryreact.libraryspringboot.controllers;

import java.util.Base64;
import java.util.List;

import com.libraryreact.libraryspringboot.config.MD5Generator;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.UsersDto;
import com.libraryreact.libraryspringboot.models.entity.UserDetail;
import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.repository.UserDetailRepository;
import com.libraryreact.libraryspringboot.repository.UsersRepository;
import com.libraryreact.libraryspringboot.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users dto) {
        StatusMessageDto<Users> response = new StatusMessageDto<>();

        try {
            dto.setPassword(MD5Generator.generate(dto.getPassword()));
            Users userCreate = usersService.register(dto);

            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("User Berhasil Dibuat!");
            response.setData(userCreate);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersDto usersDto) {
        StatusMessageDto<String> response = new StatusMessageDto<>();
        try {
            Users user = usersService.login(usersDto.getUsername(), MD5Generator.generate(usersDto.getPassword()));

            if (user == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Login Gagal!");
                return ResponseEntity.badRequest().body(response);
            }

            String baseString = user.getUsername() + ":" + user.getPassword();
            String token = Base64.getEncoder().encodeToString(baseString.getBytes());

            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Login Berhasil!");
            response.setData(token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        List<Users> user = usersRepository.findAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-detail/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Users user = usersRepository.findById(id).get();
        UserDetail userDetail = userDetailRepository.findById(user.getId() + 1).get();

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return usersService.delete(id);
    }
}
