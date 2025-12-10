package org.flipkart_daily.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Inventory {

    private Map<String,InventoryItem> inventory;
    public Inventory() {
        this.inventory = new HashMap<>();
    }

}
