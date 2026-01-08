package org.lib_mgmt_system.entity;

import lombok.Data;

@Data
public class InventoryItem {
    private long id;
    private long bookId;
    private int quantity;
}
