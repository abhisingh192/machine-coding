package org.flipkart_daily.service;

import lombok.Data;
import org.flipkart_daily.entity.Inventory;
import org.flipkart_daily.entity.InventoryItem;

@Data
public class InventoryService {
    private final Inventory inventory;
    private static long inventoryItemIdCounter = 1;

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


}
