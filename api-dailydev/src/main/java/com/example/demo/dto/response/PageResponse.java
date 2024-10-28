package com.example.demo.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageResponse<T> {

    private int pageNumber;
    private int size;
    private int totalPages;
    private int totalElements;
    private List<T> data;

}
