package com.libraryreact.libraryspringboot.controllers;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import com.libraryreact.libraryspringboot.config.JwtUtils;
import com.libraryreact.libraryspringboot.models.dto.JWTResponse;
import com.libraryreact.libraryspringboot.models.dto.LoginDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.UsersDto;
import com.libraryreact.libraryspringboot.models.entity.ERole;
import com.libraryreact.libraryspringboot.models.entity.Role;
import com.libraryreact.libraryspringboot.models.entity.UserDetail;
import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.repository.RoleRepository;
import com.libraryreact.libraryspringboot.repository.UsersRepository;
import com.libraryreact.libraryspringboot.service.UserDetailService;
import com.libraryreact.libraryspringboot.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder passEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersDto userDto) {
        StatusMessageDto<Users> response = new StatusMessageDto<>();
        // checking user exist or not
        Users user = usersRepository.findByUsername(userDto.getUsername());
        if (user != null) {
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setMessage("Username is exist!");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }

        // register account
        try {
            Users userCreated = new Users(userDto.getUsername(), passEncoder.encode(userDto.getPassword()));

            Set<String> roleStr = userDto.getRole();
            Set<Role> roles = new HashSet<>();

            roleStr.forEach(role -> {
                switch (role) {
                case "admin":
                    try {
                        Role adminRole = roleRepository.findByName(ERole.ADMIN);
                        roles.add(adminRole);
                    } catch (RuntimeException e) {
                        throw new RuntimeException("Role not found!");
                    }
                    break;

                case "peminjam":
                    try {
                        Role userRole = roleRepository.findByName(ERole.PEMINJAM);
                        roles.add(userRole);
                    } catch (RuntimeException e) {
                        // TODO: handle exception
                        throw new RuntimeException("Role not found!");
                    }
                    break;

                default:
                    try {
                        Role guestRole = roleRepository.findByName(ERole.PEMINJAM);
                        roles.add(guestRole);
                    } catch (RuntimeException e) {
                        // TODO: handle exception
                        throw new RuntimeException("Role not found!");
                    }
                    break;
                }
            });

            userCreated.setRoles(roles);
            userCreated.setIsActive(true);

            // save to repo
            usersService.register(userCreated, userDto);

            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("User created!");
            response.setData(userCreated);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        StatusMessageDto response = new StatusMessageDto<>();
        Users user = usersRepository.findByUsername(dto.getUsername());

        try {
            if (user.getIsActive().equals(true)) {
                // autentikasi user
                Authentication authentication = authManager
                        .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // generate token
                String jwt = jwtUtils.generateJwtToken(authentication);
                // get user principal
                UserDetailService userDetailService = (UserDetailService) authentication.getPrincipal();
                // get role
                Set<String> roles = userDetailService.getAuthorities().stream().map(role -> role.getAuthority())
                        .collect(Collectors.toSet());
                Integer id = jwtUtils.getIdFromToken(jwt);

                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Login success!");
                response.setData(new JWTResponse(jwt, userDetailService.getUsername(), roles, id));

                return ResponseEntity.ok().body(response);
            }
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("User tidak ditemukan");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            // response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            // response.setMessage("Error: " + e.getMessage());
            // return
            // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Username atau Password Salah");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PutMapping("/reset/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody UsersDto usersDto) {
        StatusMessageDto<Users> result = new StatusMessageDto<>();

        try {
            Users user = usersService.reset(id, usersDto);
            result.setData(user);
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Password berhasil direset!");
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

}
