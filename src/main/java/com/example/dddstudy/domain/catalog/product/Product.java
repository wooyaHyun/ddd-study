package com.example.dddstudy.domain.catalog.product;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
public class Product {
    @EmbeddedId
    private ProductId id;
}
