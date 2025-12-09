package org.flipkart_daily.entity;

import lombok.Data;

@Data
public class InventoryItem {
    private long id;
    private String brand;
    private String category;
    private int quantity;

}
