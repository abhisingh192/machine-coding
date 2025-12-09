package org.flipkart_daily.service;

import lombok.Data;
import org.flipkart_daily.entity.Product;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductService {

    private final Map<String, Product> products = new HashMap<>();
    private static long productIdCounter = 1;


    public void addOrUpdateProduct(String brand, String category, double price) {

        String key = brand + "-" + category;
        if (products.containsKey(key)) {
            Product existingProduct = products.get(key);
            existingProduct.setPrice(price);
        } else {
            Product product = new Product();
            product.setId(productIdCounter++);
            product.setBrand(brand);
            product.setCategory(category);
            product.setPrice(price);
            products.put(key, product);
        }
    }

}
