package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "vote")
public class Vote {

    @Id
    private long id;

    private int type;

    private  long userId;

    private long postId;

    private long commentId;

    private Timestamp createAt;
}
