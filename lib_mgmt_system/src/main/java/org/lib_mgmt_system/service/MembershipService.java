package org.lib_mgmt_system.service;

import lombok.Data;
import org.lib_mgmt_system.entity.Membership;
import org.lib_mgmt_system.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class MembershipService {

    private final UserService userService;
    private final Map<Long, Membership> membershipDatabase = new HashMap<>();
    private static Long membershipIdCounter = 1L;

    public void assignMembership(Long userId, String username, LocalDate startDate, LocalDate endDate) {
        User user = userService.getUserDatabase().get(userId);
        if (user != null) {
            Membership membership = new Membership();
            membership.setId(membershipIdCounter++);
            membership.setType("Standard");
            membership.setUserId(userId);
            membership.setStartDate(startDate);
            membership.setEndDate(endDate);
            membershipDatabase.put(membership.getId(), membership);
            user.setMembership(membership);

            System.out.println("Assigned membership to user: " + user.getUsername());
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }
}
