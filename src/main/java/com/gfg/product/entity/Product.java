package com.gfg.product.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "color")
    private String color;

    public Product(Long id, String productId, String title, String description, String brand,
                   BigDecimal price, String color) {
        super();
        this.id = id;
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public Product(String productId, String title, String description, String brand, BigDecimal price,
                   String color) {
        super();
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public Product() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(title, product.title) &&
                Objects.equals(description, product.description) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(price, product.price) &&
                Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, title, description, brand, price, color);
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

}
