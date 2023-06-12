package com.gaming.shop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaming.shop.exception.ProductNotFoundException;
import com.gaming.shop.models.dtos.ProductDTO;
import com.gaming.shop.models.entities.Product;
import com.gaming.shop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ObjectMapper objectMapper,
                              ProductRepository productRepository) {
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = objectMapper.convertValue(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        log.info("Product " + savedProduct.getProductBrand() + " was created!");
        return objectMapper.convertValue(savedProduct, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> productsFound = productRepository.findAll();
        List<ProductDTO> productFoundDTO = new ArrayList<>();
        productsFound.forEach(product -> productFoundDTO.add(objectMapper.convertValue(product, ProductDTO.class)));
        log.info("Product retrieved: " + productFoundDTO);
        return productFoundDTO;
    }

    @Override
    public ProductDTO updateProductById(long id, ProductDTO productDTO) {
        Product productFound = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product whit id " + id + " was not found!"));
        productFound.setProductName(productDTO.getProductName());
        productFound.setProductBrand(productDTO.getProductBrand());
        productFound.setColour(productDTO.getColour());
        productFound.setPrice(productDTO.getPrice());
        Product productSaved = productRepository.save(productFound);
        log.info("Product whit id " + id + " was updated");
        return objectMapper.convertValue(productSaved, ProductDTO.class);
    }

    @Override
    public void deleteProductById(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            log.info("Product whit id " + id + " has been deleted.");
        } else {
            throw new ProductNotFoundException("This product was not found.");
        }
    }
}
