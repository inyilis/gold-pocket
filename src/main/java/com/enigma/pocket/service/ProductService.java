package com.enigma.pocket.service;

import com.enigma.pocket.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    public Product findProductById(String id);
    public Page<Product> findProducts(Product productSearchForm, Pageable pageable);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public void removeProduct(String id);
}
