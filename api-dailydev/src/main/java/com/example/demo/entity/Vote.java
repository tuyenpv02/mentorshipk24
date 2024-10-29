package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer type;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
//    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
//    @JsonIgnore
    private Comment comment;

    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }
}
