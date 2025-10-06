package com.example.CadetInventoryManagementSystem1.repository;

import com.example.CadetInventoryManagementSystem1.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Find items by name (case-insensitive, partial match)
     */
    List<Item> findByNameContainingIgnoreCase(String name);

    /**
     * Find items by locker number
     */
    List<Item> findByLockerNumber(String lockerNumber);

    /**
     * Search items by name or locker number (for search functionality)
     */
    @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(i.lockerNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(i.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Item> searchItems(@Param("searchTerm") String searchTerm);
}
