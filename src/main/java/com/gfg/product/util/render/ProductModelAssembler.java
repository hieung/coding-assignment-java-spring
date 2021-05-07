package com.gfg.product.util.render;

import com.gfg.product.controller.ProductController;
import com.gfg.product.entity.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product product) {

        EntityModel<Product> productModel = EntityModel.of(product,
                linkTo(methodOn(ProductController.class).findByIdWithLinkRel(product.getId())).withSelfRel().withType(HttpMethod.GET.toString()));
        productModel.add(linkTo(ProductController.class).slash("create").withRel("create").withType(HttpMethod.POST.toString()));
        productModel.add(linkTo(ProductController.class).slash("updateById/" + product.getId()).withRel("update").withType(HttpMethod.PUT.toString()));
        productModel.add(linkTo(ProductController.class).slash("deleteById/" + product.getId()).withRel("delete").withType(HttpMethod.DELETE.toString()));

        return productModel;
    }

}