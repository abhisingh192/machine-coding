package org.lib_mgmt_system.service;

import lombok.Data;
import lombok.var;
import org.lib_mgmt_system.entity.Inventory;
import org.lib_mgmt_system.entity.InventoryItem;

@Data
class InventoryService {
    private final Inventory inventory;

    public InventoryService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addInventoryItem(long bookId, int quantity) {
        long itemId = inventory.getItems().size() + 1L;
        var item = new InventoryItem();
        item.setId(itemId);
        item.setBookId(bookId);
        item.setQuantity(quantity);
        inventory.getItems().put(itemId, item);
        System.out.println("Added inventory item: Book ID " + bookId + ", Quantity " + quantity);
    }

    public void updateInventoryItem(long itemId, int quantity) {
        var item = inventory.getItems().get(itemId);
        if (item != null) {
            item.setQuantity(quantity);
            System.out.println("Updated inventory item ID " + itemId + " to Quantity " + quantity);
        } else {
            System.out.println("Inventory item ID " + itemId + " not found.");
        }
    }

    public void removeInventoryItem(long itemId) {
        if (inventory.getItems().remove(itemId) != null) {
            System.out.println("Removed inventory item ID " + itemId);
        } else {
            System.out.println("Inventory item ID " + itemId + " not found.");
        }
    }

    public void showInventory() {
        System.out.println("Current Inventory:");
        for (var item : inventory.getItems().values()) {
            System.out.println("Item ID: " + item.getId() + ", Book ID: " + item.getBookId() + ", Quantity: " + item.getQuantity());
        }
    }


}
