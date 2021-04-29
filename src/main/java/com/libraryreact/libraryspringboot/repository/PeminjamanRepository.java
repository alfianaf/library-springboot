package com.libraryreact.libraryspringboot.repository;

import java.util.List;
// import java.util.Map;
import java.util.Set;

import com.libraryreact.libraryspringboot.models.entity.Peminjaman;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Integer> {
    public List<Peminjaman> findByIsFinished(Boolean isFinished);

    @Query(value = "select * from peminjaman where id = ?", nativeQuery = true)
    public Peminjaman findDetailById(Integer userId);

    @Query(value = "select * from peminjaman where user_id = ? order by is_finished asc", nativeQuery = true)
    public List<Peminjaman> getriwayatsewa(Integer id);

    @Query(value = "select kode_buku.id_buku from peminjaman join kode_buku on peminjaman.kode_buku = kode_buku.kode_buku group by id_buku order by count(kode_buku.id_buku) desc limit 6", nativeQuery = true)
    public Set<Integer> findIdBukuTerpopuler();
}
