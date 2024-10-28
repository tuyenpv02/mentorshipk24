package com.example.demo.service;

import com.example.demo.dto.SignUpDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {

    //
    String login(UserLoginDTO userLoginDTO);

    String signUp(SignUpDTO signUpDTO);

    boolean checkEmail(String email);


    //
    Page<Account> getAll(int page, int size);

    Account findById(Long id);

    Account add(Account user);

    Account update(Long id, Account user);

    Boolean existsById(Long id);
}
