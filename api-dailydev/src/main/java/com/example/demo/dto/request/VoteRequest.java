package com.example.demo.dto.request;

import com.example.demo.entity.Account;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoteRequest {

    private Long id;

    @NotNull
    private Integer type;

    @NotNull
    private Long accountId;

    //    @NotNull
    private Long postId;

    private Long commentId;

//    private Timestamp createAt;
}
