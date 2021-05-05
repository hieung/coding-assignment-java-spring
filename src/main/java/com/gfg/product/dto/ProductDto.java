package com.gfg.product.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto {

    private Long id;
    private String productId;
    private String title;
    private String description;
    private String brand;
    private BigDecimal price;
    private String color;

    public ProductDto(Long id, String productId, String title, String description, String brand, BigDecimal price, String color) {
        this.id = id;
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public ProductDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto)) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(price, that.price) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, title, description, brand, price, color);
    }
}
