package com.gaming.shop.services;

import com.gaming.shop.models.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getProducts();

    ProductDTO updateProductById(long id, ProductDTO productDTO);

    void deleteProductById(long id);
}
