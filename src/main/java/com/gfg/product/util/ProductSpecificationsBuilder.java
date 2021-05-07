package com.gfg.product.util;

import com.gfg.product.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSpecificationsBuilder {
    
    private final List<SearchCriteria> params;

    public ProductSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public ProductSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Product> build(){
        if (params.isEmpty()) {
            return null;
        }

        List<? extends Specification> specs = params.stream()
          .map(ProductSpecification::new)
          .collect(Collectors.toList());

        Specification<Product> result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
              .isOrPredicate()
                ? Specification.where(result)
                  .or(specs.get(i))
                : Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}