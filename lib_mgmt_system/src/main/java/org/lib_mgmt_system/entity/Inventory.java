package org.lib_mgmt_system.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Inventory {
    private Map<Long,InventoryItem> items =  new HashMap<>();  // Key: InventoryItemid, Value: InventoryItem
}
