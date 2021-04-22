package com.libraryreact.libraryspringboot.repository;

import com.libraryreact.libraryspringboot.models.entity.SaldoLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoLogRepository extends JpaRepository<SaldoLog, Integer> {
    @Query(value = "select * from saldo_log where user_id = ?", nativeQuery = true)
    public SaldoLog findByUserId(Integer userId);
}
