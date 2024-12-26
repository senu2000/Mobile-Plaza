package com.senu.microservices.order.service;

import com.senu.microservices.order.dto.OrderRequest;

public interface OrderService {

    void placeOrder(OrderRequest orderRequest);
}
