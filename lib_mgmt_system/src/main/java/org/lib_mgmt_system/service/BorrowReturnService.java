package org.lib_mgmt_system.service;

import lombok.Data;
import org.lib_mgmt_system.entity.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Data
public class BorrowReturnService {
    private final UserService userService;
    private final MembershipService membershipService;
    private final InventoryService inventoryService;


    public void borrowBook(Long userId, Long bookId, LocalDate issueDate) {
        User user = userService.getUserDatabase().get(userId);

        if(user == null) {
            System.out.println("User not found with ID: " + userId);
            return;
        }

        if (user.getMembership() == null || user.getMembership().getEndDate().isBefore(issueDate)) {
            System.out.println("User " + user.getUsername() + " does not have a valid membership to borrow books.");
            return;
        }

        if(user.getBookIdBorrowedDateMap().containsKey(bookId)) {
            System.out.println("User " + user.getUsername() + " has already borrowed book ID: " + bookId);
            return;
        }

        if(user.getBookIdBorrowedDateMap().size() >= 2) {
            System.out.println("User " + user.getUsername() + " has reached the maximum borrow limit of 2");
            return;
        }

        LocalDate dueDate = issueDate.plusDays(30)
                .compareTo(user.getMembership().getEndDate()) <= 0
                ? issueDate.plusDays(30)
                : user.getMembership().getEndDate();
        user.getBookIdBorrowedDateMap().put(bookId, Map.of(issueDate, dueDate));
        // decrease inventory count
        inventoryService.getInventory().getItems().values().stream()
                .filter(item -> item.getBookId()==bookId)
                .findFirst()
                .ifPresent(item -> {
                    if (item.getQuantity() > 0) {
                        item.setQuantity(item.getQuantity() - 1);
                    } else {
                        System.out.println("Book ID: " + bookId + " is out of stock.");
                    }
                });
        System.out.println("User " + user.getUsername() + " borrowed book ID: " + bookId + " on " + issueDate);

    }

    public void returnBook(Long userId, Long bookId, LocalDate returnDate) {
        User user = userService.getUserDatabase().get(userId);

        if(user == null) {
            System.out.println("User not found with ID: " + userId);
            return;
        }

        if (!user.getBookIdBorrowedDateMap().containsKey(bookId)) {
            System.out.println("User " + user.getUsername() + " has not borrowed book ID: " + bookId);
            return;
        }

        Map<LocalDate, LocalDate> borrowDates = user.getBookIdBorrowedDateMap().get(bookId);
        LocalDate dueDate = borrowDates.values().iterator().next();

        if (returnDate.isAfter(dueDate)) {
            System.out.println("User " + user.getUsername() + " returned book ID: " + bookId + " late on " + returnDate);
            // apply a fine of rs 1 for each day late
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            System.out.println("Fine applied: Rs " + daysLate);
        } else {
            System.out.println("User " + user.getUsername() + " returned book ID: " + bookId + " on time on " + returnDate);
        }

        user.getBookIdBorrowedDateMap().remove(bookId);
        // increase inventory count
        inventoryService.getInventory().getItems().values().stream()
                .filter(item -> item.getBookId()==bookId)
                .findFirst()
                .ifPresent(item -> item.setQuantity(item.getQuantity() + 1));
    }
}
