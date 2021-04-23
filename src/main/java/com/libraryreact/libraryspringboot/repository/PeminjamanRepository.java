package com.libraryreact.libraryspringboot.repository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.entity.Peminjaman;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Integer> {
    public List<Peminjaman> findByIsFinished(Boolean isFinished);

    @Query(value = "select * from peminjaman where id = ?", nativeQuery = true)
    public Peminjaman findDetailById(Integer userId);
}
