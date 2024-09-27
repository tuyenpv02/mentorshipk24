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
@Table(name = "squad")
public class Squad {

    @Id
    private long id;

    private String name;

    private String userName;

    private String description;

    private int type;

    private int typeCategory;

    private Timestamp createAt;
}
