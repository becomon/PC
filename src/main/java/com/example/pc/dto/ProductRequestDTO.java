package com.example.pc.dto;

import com.example.pc.model.Store;

import java.math.BigDecimal;

public class ProductRequestDTO {
    private Long id;
    private Store store;
    private String name;
    private BigDecimal price;
}
