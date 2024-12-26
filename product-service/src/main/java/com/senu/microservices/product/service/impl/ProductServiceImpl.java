package com.senu.microservices.product.service.impl;

import com.senu.microservices.product.dto.ProductRequest;
import com.senu.microservices.product.dto.ProductResponse;
import com.senu.microservices.product.model.Product;
import com.senu.microservices.product.repository.ProductRepository;
import com.senu.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .skuCode(productRequest.skuCode())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product Created Successfully");
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSkuCode(),
                product.getPrice()
        );
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(
                        product -> new ProductResponse(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getSkuCode(),
                                product.getPrice()
                        )
                )
                .toList();
    }
}
