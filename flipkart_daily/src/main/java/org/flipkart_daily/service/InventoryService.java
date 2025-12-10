package org.flipkart_daily.service;

import lombok.Data;
import org.flipkart_daily.entity.Inventory;
import org.flipkart_daily.entity.InventoryItem;

import java.util.HashMap;

@Data
public class InventoryService {
    private final Inventory inventory;
    private static long inventoryItemIdCounter = 1;

    public InventoryService() {
        this.inventory = new Inventory();
    }

    public void addOrUpdateInventory(String brand, String category, int quantity) {
        String key = brand + "-" + category;

        if (inventory.getInventory().containsKey(key)) {
            InventoryItem existingItem = inventory.getInventory().get(key);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);

        } else {
            InventoryItem item = new InventoryItem();
            item.setId(inventoryItemIdCounter++);
            item.setBrand(brand);
            item.setCategory(category);
            item.setQuantity(quantity);

            inventory.getInventory().put(key, item);
        }
    }

    public void viewInventory() {
        inventory.getInventory().forEach((key, item) -> {
            System.out.println(item.getBrand() + "->" + item.getCategory() + "->" + item.getQuantity());
        });
    }


}
