package com.example.dddstudy.catalog.product;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Product {
    @EmbeddedId
    private ProductId id;
}
