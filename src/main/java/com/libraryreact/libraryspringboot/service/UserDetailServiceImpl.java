package com.libraryreact.libraryspringboot.service;

import javax.transaction.Transactional;

import com.libraryreact.libraryspringboot.models.dto.SaldoDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.UsersDto;
import com.libraryreact.libraryspringboot.models.entity.UserDetail;
import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.repository.UserDetailRepository;
import com.libraryreact.libraryspringboot.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailServiceImpl implements UsersService, UserDetailsService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserDetailRepository userDetailRepository;
    @Autowired
    private PasswordEncoder passEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found!");
        }
        return UserDetailService.build(user);
    }

    @Override
    public ResponseEntity<?> edit(Integer id, UsersDto userDto) {
        StatusMessageDto<UserDetail> response = new StatusMessageDto<>();

        Users user = usersRepository.findById(id).get();
        UserDetail detailUser = userDetailRepository.findDetailByUserId(user.getId());
        detailUser.setNama(userDto.getNama());
        detailUser.setAlamat(userDto.getAlamat());
        detailUser.setFoto(userDto.getFoto());
        detailUser.setKelamin(userDto.getKelamin());
        detailUser.setNik(userDto.getNik());
        // detailUser.setSaldo(0);
        detailUser.setTanggalLahir(userDto.getTanggalLahir());
        detailUser.setTelp(userDto.getTelp());
        detailUser.setTempatLahir(userDto.getTempatLahir());
        userDetailRepository.save(detailUser);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("User Berhasil Diubah!");
        response.setData(detailUser);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        StatusMessageDto<Users> response = new StatusMessageDto<>();

        Users user = usersRepository.findById(id).get();
        UserDetail detailUser = userDetailRepository.findDetailByUserId(user.getId());
        detailUser.setIsActive(false);
        user.setIsActive(false);
        userDetailRepository.save(detailUser);
        usersRepository.save(user);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("User Berhasil Dihapus!");
        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @Override
    public Users register(Users user, UsersDto userDto) {
        UserDetail detailUser = new UserDetail();
        detailUser.setNama(userDto.getNama());
        detailUser.setAlamat(userDto.getAlamat());
        detailUser.setFoto(userDto.getFoto());
        detailUser.setKelamin(userDto.getKelamin());
        detailUser.setNik(userDto.getNik());
        detailUser.setSaldo(0.0);
        detailUser.setTanggalLahir(userDto.getTanggalLahir());
        detailUser.setTelp(userDto.getTelp());
        detailUser.setTempatLahir(userDto.getTempatLahir());
        detailUser.setUser(user);

        detailUser.setIsActive(true);
        usersRepository.save(user);

        userDetailRepository.save(detailUser);
        return user;
    }

    @Override
    public Users login(String username, String password) {
        Users user = usersRepository.findByUsername(username);
        return user;
    }

    @Override
    public ResponseEntity<?> tambahSaldo(Integer id, SaldoDto saldoDto) {
        StatusMessageDto<UserDetail> response = new StatusMessageDto<>();

        Users user = usersRepository.findById(id).get();
        UserDetail detailUser = userDetailRepository.findDetailByUserId(user.getId());
        detailUser.setSaldo(detailUser.getSaldo() + saldoDto.getSaldo());
        userDetailRepository.save(detailUser);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Saldo sejumlah Rp. " + saldoDto.getSaldo()
                + " berhasil ditambahkan, saldo akhir anda : Rp. " + detailUser.getSaldo());
        response.setData(detailUser);
        return ResponseEntity.ok(response);
    }

    @Override
    public Users reset(Integer id, UsersDto usersDto) {
        Users user = usersRepository.findById(id).get();
        user.setPassword(passEncoder.encode(usersDto.getPassword()));
        usersRepository.save(user);
        // TODO Auto-generated method stub
        return user;
    }

}
