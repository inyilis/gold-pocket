package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Product;
import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.service.ProductHistoryPriceService;
import com.enigma.pocket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductHistoryPriceService productHistoryPriceService;

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(name = "id") String id){
        Product product = productService.findProductById(id);
        product.setProductHistoryPrices(null);
        return product;
    }

    @GetMapping("/product/{id}/history")
    public List<ProductHistoryPrice> getHistoriesByProduct(@PathVariable(name = "id") String id){
        return productHistoryPriceService.findAllByProduct(id);
    }

    @GetMapping("/products")
    public Page<Product> getAllProducts(@RequestBody Product productSearchForm,
                                        @RequestParam(name = "page", defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", defaultValue = "5") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return productService.findProducts(productSearchForm, pageable);
    }

    @PostMapping("/product")
    public void createNewProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @PutMapping("/product")
    public void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public void removeProduct(@PathVariable(name = "id") String id){
        productService.removeProduct(id);
    }
}
