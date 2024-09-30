package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Account findById(Long id) {
        Optional<Account> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }

    public Account add(Account user) {
        return repository.save(user);
    }

    public Account update(Long id, Account newUser) {
        Optional<Account> optional = repository.findById(id);
        return optional.map(o -> {
            o.setUsername(newUser.getUsername());
            o.setEmail(newUser.getEmail());
            o.setPassword(newUser.getPassword());
            return repository.save(o);
        }).orElse(null);
    }

    public Account deleteById(Long id) {
        Optional<Account> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
