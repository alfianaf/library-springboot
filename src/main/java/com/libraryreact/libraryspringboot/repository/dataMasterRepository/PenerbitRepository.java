package com.libraryreact.libraryspringboot.repository.dataMasterRepository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.entity.dataMaster.Penerbit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PenerbitRepository extends JpaRepository<Penerbit, Integer> {
       // cari berdasarkan id yang belum dihapus
       @Query(value = "select * from penerbit where is_deleted = 0 and id = ?", nativeQuery = true)
       public Penerbit findByIdPenerbit(Integer id);
   
       // tampil semua data berdasarkan status is deleted - nya
       public List<Penerbit> findByIsDeleted(boolean isDeleted);
   
       // cari berdasarkan nama Penerbit dan yang belum dihapus
       @Query(value = "select * from penerbit where is_deleted = 0 and nama_penerbit = ?", nativeQuery = true)
       public Penerbit findByNamaPenerbit(String namaPenerbit);
}
