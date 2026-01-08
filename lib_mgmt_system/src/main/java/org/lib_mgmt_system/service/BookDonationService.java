package org.lib_mgmt_system.service;

import lombok.Data;

@Data
public class BookDonationService {

    private final UserService userService;
    private final InventoryService inventoryService;

    public BookDonationService(UserService userService, InventoryService inventoryService) {
        this.userService = userService;
        this.inventoryService = inventoryService;
    }

    public void donateBooks(Long userId, Long bookId, int quantity) {
        // Process the donation
        if (!userService.getUserDatabase().containsKey(userId)) {
            System.out.println("User ID " + userId + " not found. Donation failed.");
            return;
        }

        if (inventoryService.getInventory().getItems().containsKey(bookId)) {
            inventoryService.getInventory().getItems().get(bookId).setQuantity(
                    inventoryService.getInventory().getItems().get(bookId).getQuantity() + quantity
            );
        } else {
            inventoryService.addInventoryItem(bookId, quantity);
        }
        // extend user membership by 90 days
        userService.getUserDatabase().get(userId).getMembership()
                .setEndDate(userService.getUserDatabase().get(userId).getMembership().getEndDate().plusDays(90));
        System.out.println("User " + userId + " donated " + quantity + " copies of book ID " + bookId);
    }
}
