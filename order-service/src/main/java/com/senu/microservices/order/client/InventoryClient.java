package com.senu.microservices.order.client;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(value = "inventory", url = "${inventory.url}")
//public interface InventoryClient {
//
//    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
//    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
//}


@Slf4j
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
//    @TimeLimiter()
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable){
        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }

}