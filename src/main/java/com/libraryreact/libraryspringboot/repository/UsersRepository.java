package com.libraryreact.libraryspringboot.repository;

import com.libraryreact.libraryspringboot.models.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    public Users findByUsername(String username);

    @Query(value = "select username from users where username = ?", nativeQuery = true)
    public String findUsernameByUsername(String username);
}
