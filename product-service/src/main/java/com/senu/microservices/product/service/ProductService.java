package com.senu.microservices.product.service;

import com.senu.microservices.product.dto.ProductRequest;
import com.senu.microservices.product.dto.ProductResponse;
import com.senu.microservices.product.model.Product;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
