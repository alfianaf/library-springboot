package com.libraryreact.libraryspringboot.controllers.peminjam;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.BukuUsersDto;
import com.libraryreact.libraryspringboot.models.entity.Peminjaman;
import com.libraryreact.libraryspringboot.models.entity.SaldoLog;
import com.libraryreact.libraryspringboot.service.PeminjamanService;
import com.libraryreact.libraryspringboot.service.SaldoLogService;
import com.libraryreact.libraryspringboot.service.dataBukuService.BukuUsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class PeminjamController {
    @Autowired
    private BukuUsersService bukuService;
    @Autowired
    private PeminjamanService peminjamanService;
    @Autowired
    private SaldoLogService saldoLogService;

    // get all book for user
    @GetMapping("/buku/all")
    public ResponseEntity<?> getAllCollections(){
        try {
            StatusMessageDto<List<BukuUsersDto>> response = new StatusMessageDto<>();
            List<BukuUsersDto> BukuDtos = bukuService.getAllCollections();
            
            if(BukuDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar buku kosong!");
                response.setData(BukuDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar buku ditemukan!");
                response.setData(BukuDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // get terpopuler

    // get terbaru
    @GetMapping("/buku/terbaru")
    public ResponseEntity<?> getNewCollections(){
        try {
            StatusMessageDto<List<BukuUsersDto>> response = new StatusMessageDto<>();
            List<BukuUsersDto> BukuDtos = bukuService.getNewCollections();
            
            if(BukuDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar buku kosong!");
                response.setData(BukuDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar buku ditemukan!");
                response.setData(BukuDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // get terpopuler
    @GetMapping("/buku/terpopuler")
    public ResponseEntity<?> getHotCollections(){
        try {
            StatusMessageDto<List<BukuUsersDto>> response = new StatusMessageDto<>();
            List<BukuUsersDto> BukuDtos = bukuService.getHotCollections();
            
            if(BukuDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar buku kosong!");
                response.setData(BukuDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar buku ditemukan!");
                response.setData(BukuDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // get donasi by id user
    @GetMapping("/buku/donasi/{id}")
    public ResponseEntity<?> getDonationByIdUser(@PathVariable Integer id){
        try {
            StatusMessageDto<List<BukuUsersDto>> response = new StatusMessageDto<>();
            List<BukuUsersDto> BukuDtos = bukuService.getDonationByIdUser(id);
            
            if(BukuDtos.size() == 0){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Daftar buku kosong!");
                response.setData(BukuDtos);
                return ResponseEntity.badRequest().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Daftar buku ditemukan!");
                response.setData(BukuDtos);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // get semua riwayat sewa (selesai dan sedang disewa) by id user peminjam
    @GetMapping("/riwayat/sewa/{id}")
    public ResponseEntity<?> getRiwayatSewa(@PathVariable Integer id){
        try {
            StatusMessageDto<List<Peminjaman>> response = peminjamanService.riwayatSewaByPeminjam(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // get buku yang sedang disewa berdasarkan id user peminjam
    @GetMapping("/riwayat/sedangdisewa/{id}")
    public ResponseEntity<?> getSedangDisewa(@PathVariable Integer id){
        try {
            StatusMessageDto<List<Peminjaman>> response = peminjamanService.sedangDisewaByPeminjam(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // get riwayat sewa berstatus selesai by id user peminjam
    @GetMapping("/riwayat/selesai/{id}")
    public ResponseEntity<?> getRiwayatSewaSelesai(@PathVariable Integer id){
        try {
            StatusMessageDto<List<Peminjaman>> response = peminjamanService.riwayatSewaSelesaiByPeminjam(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // get riwayat saldo berdasarkan id user
    @GetMapping("/riwayat/saldo/{id}")
    public ResponseEntity<?> getRiwayatSaldo(@PathVariable Integer id){
        try {
            StatusMessageDto<List<SaldoLog>> response = new StatusMessageDto<>();
            List<SaldoLog> list = saldoLogService.getLogSaldoByIdUser(id);
            if(list == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Data tidak ditemukan ... !");
                response.setData(list);
                return ResponseEntity.ok().body(response);
            }
            else{
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Data berhasil didapatkan ... !");
                response.setData(list);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
