package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_squad")
public class UserSquad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  Long userId;

    private Long squadId;

    private int role;

    private Timestamp createAt;
}
