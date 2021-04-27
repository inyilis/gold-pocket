package com.enigma.pocket.service;

import com.enigma.pocket.entity.Product;
import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.repository.ProductRepository;
import com.enigma.pocket.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;


@Service
public class ProductServiceImpl implements ProductService {

    private final String notFoundMessage = "Customer with id: %s not found!";

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductHistoryPriceService productHistoryPriceService;

    @Override
    public Product findProductById(String id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    @Override
    public Page<Product> findProducts(Product productSearchForm, Pageable pageable) {
        return productRepository.findAll(ProductSpecification.findProduct(productSearchForm), pageable);
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreatedAt(new Date());
        return persistProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        validatePresent(product.getId());
        return persistProduct(product);
    }

    @Override
    public void removeProduct(String id) {
        validatePresent(id);
        productRepository.deleteById(id);
    }

    private Product persistProduct(Product product) {
        product.setUpdatedAt(new Date());
        // save ke product
        Product saveProduct = productRepository.save(product);
        // save ke history
        ProductHistoryPrice productHistoryPrice = new ProductHistoryPrice(saveProduct);
        productHistoryPriceService.logPrice(productHistoryPrice);
        return saveProduct;
    }

    private void validatePresent(String id) {
        if(!productRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(notFoundMessage, id));
        }
    }
}
