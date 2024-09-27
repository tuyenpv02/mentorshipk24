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
@Table(name = "post")
public class Post {

    @Id
    private long id;

    private String title;

    private String url;

    private String description;

    private long tag;

    private  long userId;

    private long squadId;

    private Timestamp pubDate;

    private Timestamp createAt;
}
