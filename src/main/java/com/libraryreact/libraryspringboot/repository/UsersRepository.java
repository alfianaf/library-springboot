package com.libraryreact.libraryspringboot.repository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    public Users findByUsername(String username);

    @Query(value = "select username from users where username = ?", nativeQuery = true)
    public String findUsernameByUsername(String username);

    @Query(value = "select * from users where is_active = 1 and id = ?", nativeQuery = true)

    public Users findActiveById(Integer id);

    public List<Users> findByIsActive(Boolean isActive);

    @Query(value = "select * from users where is_active = 1 and id = ?", nativeQuery = true)
    public Users findByIdUser(Integer id);

    @Query(value = "select * from users where is_active = 1 and username = ?", nativeQuery = true)
    public Users findByUsernameUser(String username);
}
