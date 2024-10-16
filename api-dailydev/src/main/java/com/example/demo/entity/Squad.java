package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "squad")
public class Squad {
    @Id
    private long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;

    private String userName;

    private String description;

    private Integer type;

    private Integer typeCategory;
}
