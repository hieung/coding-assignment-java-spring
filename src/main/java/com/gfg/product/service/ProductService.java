package com.gfg.product.service;

import com.gfg.product.dto.ProductDto;
import com.gfg.product.entity.Product;
import com.gfg.product.exception.NoDataFoundException;
import com.gfg.product.exception.ProductNotFoundException;
import com.gfg.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll() {
        List<Product> result = productRepository.findAll();
        if (result.isEmpty()) {
            throw new NoDataFoundException();
        }
        return result;
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

}
