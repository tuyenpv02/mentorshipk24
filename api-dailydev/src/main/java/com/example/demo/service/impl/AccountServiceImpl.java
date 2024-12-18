package com.example.demo.service.impl;

import com.example.demo.dto.SignUpDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;

    public Boolean login(UserLoginDTO userLoginDTO) {
        Optional<Account> account = repository.findByEmail(userLoginDTO.getEmail());
        if (userLoginDTO.getPassword().equals(account.get().getPassword())) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(String email) {
        Optional<Account> optional = repository.findByEmail(email);
        if (optional.isPresent()) {
            return true;
        }
        return false;
    }

    public String signUp(SignUpDTO signUpDTO) {
        Account account = Account.builder()
                .username(signUpDTO.getUsername())
                .email(signUpDTO.getEmail())
                .password(signUpDTO.getPassword()).build();
        repository.save(account);
        return "Đăng ký thành công";
    }


    public Page<Account> getAll(int page , int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return repository.findAll(pageable);
    }

    public Account findById(Long id) {
        Optional<Account> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }

    public Account add(Account user) {
        return repository.save(user);
    }

    public Account update(Long id, Account user) {
        Optional<Account> optional = repository.findById(id);
        return optional.map(o -> {
            o.setUsername(user.getUsername());
            o.setEmail(user.getEmail());
            o.setPassword(user.getPassword());
            return repository.save(o);
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
