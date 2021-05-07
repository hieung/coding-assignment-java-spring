package com.gfg.product.service;

import com.gfg.product.dto.ProductDto;
import com.gfg.product.entity.Product;
import com.gfg.product.exception.NoDataFoundException;
import com.gfg.product.exception.ProductNotFoundException;
import com.gfg.product.repository.ProductRepository;
import com.gfg.product.util.ProductSpecificationsBuilder;
import com.gfg.product.util.render.ProductModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PagedResourcesAssembler<Product> pagedResourcesAssembler;

    @Autowired
    ProductModelAssembler productModelAssembler;

    public List<Product> findAll() {
        List<Product> result = productRepository.findAll();
        if (result.isEmpty()) {
            throw new NoDataFoundException();
        }
        return result;
    }

    public PagedModel<EntityModel<Product>> findAllPaging(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(productPage);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product(productDto.getProductId(), productDto.getTitle(), productDto.getDescription(), productDto.getBrand(), productDto
                .getPrice(), productDto.getColor());
        return productRepository.save(product);
    }

    public Product updateProduct(@RequestBody ProductDto product, @PathVariable Long id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.setId(id);
        if (product.getProductId() != null && !product.getProductId().trim().isEmpty()) {
            existingProduct.setProductId(product.getProductId());
        }

        if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
            existingProduct.setBrand(product.getBrand());
        }

        if (product.getColor() != null && !product.getColor().trim().isEmpty()) {
            existingProduct.setColor(product.getColor());
        }

        if (product.getTitle() != null && !product.getTitle().trim().isEmpty()) {
            existingProduct.setTitle(product.getTitle());
        }

        if (product.getDescription() != null && !product.getDescription().trim().isEmpty()) {
            existingProduct.setDescription(product.getDescription());
        }

        if (product.getPrice() != null && product.getPrice().doubleValue() > 0) {
            existingProduct.setPrice(product.getPrice());
        }

        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(@PathVariable Long id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (existingProduct != null) {
            productRepository.delete(existingProduct);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public EntityModel<Product> findByIdWithLinkRel(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productModelAssembler.toModel(product);
    }

    public PagedModel<EntityModel<Product>> searchAll(String search, Pageable pageable) {
        ProductSpecificationsBuilder builder = new ProductSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Product> spec = builder.build();

        Page<Product> productPage = productRepository.findAll(spec,pageable);

        return pagedResourcesAssembler.toModel(productPage);
    }

}