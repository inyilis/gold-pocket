package com.enigma.pocket.service;

import com.enigma.pocket.entity.Product;
import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.repository.ProductHistoryPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductHistoryPriceServiceImpl implements ProductHistoryPriceService {

    @Autowired
    ProductHistoryPriceRepository productHistoryPriceRepository;

    @Autowired
    ProductService productService;

    @Override
    public ProductHistoryPrice logPrice(ProductHistoryPrice productHistoryPrice) {
        return productHistoryPriceRepository.save(productHistoryPrice);
    }

    @Override
    public List<ProductHistoryPrice> findAllPrice() {
        return productHistoryPriceRepository.findAll();
    }

    @Override
    public List<ProductHistoryPrice> findAllByProduct(String productId) {
        Product product = productService.findProductById(productId);
        return product.getProductHistoryPrices();
    }
}
