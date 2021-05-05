package com.gfg.product.controller;

import com.gfg.product.dto.ProductDto;
import com.gfg.product.entity.Product;
import com.gfg.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/findAll", produces = "application/vnd.gfg.v1+json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/findById/{id}", produces = "application/vnd.gfg.v1+json")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(value = "/create", produces = "application/vnd.gfg.v1+json")
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping(value = "/updateById/{id}", produces = "application/vnd.gfg.v1+json")
    public Product updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id) {
        return productService.updateProduct(productDto, id);
    }

    @DeleteMapping(value = "/deleteById/{id}", produces = "application/vnd.gfg.v1+json")
    public boolean deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }


}