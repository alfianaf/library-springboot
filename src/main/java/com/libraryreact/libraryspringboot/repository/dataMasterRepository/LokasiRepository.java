package com.libraryreact.libraryspringboot.repository.dataMasterRepository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.entity.dataMaster.Lokasi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Integer>{
     // cari berdasarkan id yang belum dihapus
     @Query(value = "select * from lokasi where is_deleted = 0 and id = ?", nativeQuery = true)
     public Lokasi findByIdLokasi(Integer id);
 
     // tampil semua data berdasarkan status is deleted - nya
     public List<Lokasi> findByIsDeleted(boolean isDeleted);
 
     // cari berdasarkan Kode Lokasi dan yang belum dihapus
     @Query(value = "select * from lokasi where is_deleted = 0 and kode_lokasi = ?", nativeQuery = true)
     public Lokasi findByKodeLokasi(String kodeLokasi);
}
