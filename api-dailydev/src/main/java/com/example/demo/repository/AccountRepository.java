package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    @Query(value = "select id, username, email, createAt  from account where id = ?1"
//            , countQuery = "select count(id) from account where id = ?1"
//            , nativeQuery = true)
//    Page<Account> findAll(Long id, Pageable pageable);

    Optional<Account> findByEmail(String email);
}
