package com.gfg.product.controller;

import com.gfg.product.dto.ProductDto;
import com.gfg.product.entity.Product;
import com.gfg.product.service.ProductService;
import com.gfg.product.util.ProductSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/findAll", produces = "application/vnd.gfg.v1+json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/findAll", produces = {"application/vnd.gfg.v2+json", "application/hal+json"})
    public ResponseEntity<PagedModel<EntityModel<Product>>> findAllPaging(Pageable pageable) {
        return ResponseEntity.ok().body(productService.findAllPaging(pageable));
    }

    @GetMapping(value = "/findById/{id}", produces = "application/vnd.gfg.v1+json")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping(value = "/findById/{id}", produces = {"application/vnd.gfg.v2+json", "application/hal+json"})
    public ResponseEntity<EntityModel<Product>> findByIdWithLinkRel(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findByIdWithLinkRel(id));
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

    @GetMapping(value = "/searchAll")
    public ResponseEntity<PagedModel<EntityModel<Product>>> search(@RequestParam(value = "search") String search, Pageable pageable) {
        return ResponseEntity.ok().body(productService.searchAll(search, pageable));
    }

}