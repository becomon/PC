package com.example.pc.dto;

import java.util.List;

public class ListProductDTO {
    List<ProductRequestDTO> content;
    private int pages;
    private int size;
    private Long totalElements;
}
