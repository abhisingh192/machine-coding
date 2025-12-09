package org.flipkart_daily.service;

import org.flipkart_daily.entity.Inventory;
import org.flipkart_daily.entity.InventoryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchService {
    
    private final InventoryService inventoryService;
    private final ProductService productService;
    
    public SearchService(InventoryService inventoryService, ProductService productService) {
        this.inventoryService = inventoryService;
        this.productService = productService;
    }
    
    public void searchItems(List<String> searchText) {
        List<String> keys = new ArrayList<>();
        List<InventoryItem> inventoryItems = inventoryService.getInventory().getInventory().values().stream().toList();
        
        for (String text : searchText) {
            
            InventoryItem item = (InventoryItem) inventoryItems.stream()
                    .filter(x -> x.getCategory().equalsIgnoreCase(text) || x.getBrand().equalsIgnoreCase(text))
                    .map(x->x);
            
            keys.add(item.getBrand() + "-" + item.getCategory());
        }
        
        for (String key : keys) {
            System.out.println(productService.getProducts().get(key).getBrand() + "->" +
                    productService.getProducts().get(key).getCategory() + "->" +
                    productService.getProducts().get(key).getPrice() + "->" +
                    inventoryService.getInventory().getInventory().get(key).getQuantity());
        }
    }
    
}
