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
@Table(name = "follow_tag")
public class FollowTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  Long userId;

    private Long tagId;

    private Timestamp createAt;
}
