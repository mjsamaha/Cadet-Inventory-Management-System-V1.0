package com.example.CadetInventoryManagementSystem1.service;

import com.example.CadetInventoryManagementSystem1.entity.Item;
import com.example.CadetInventoryManagementSystem1.exception.ItemNotFoundException;
import com.example.CadetInventoryManagementSystem1.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Create a new item
     */
    public Item createItem(Item item) {
        // Ensure id is null for new items
        item.setId(null);
        return itemRepository.save(item);
    }

    /**
     * Get all items
     */
    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Get item by ID
     */
    @Transactional(readOnly = true)
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    /**
     * Update an existing item
     */
    public Item updateItem(Long id, Item itemDetails) {
        Item existingItem = getItemById(id);
        
        // Update fields
        existingItem.setQuantity(itemDetails.getQuantity());
        existingItem.setName(itemDetails.getName());
        existingItem.setLockerNumber(itemDetails.getLockerNumber());
        existingItem.setDescription(itemDetails.getDescription());
        
        return itemRepository.save(existingItem);
    }

    /**
     * Delete an item by ID
     */
    public void deleteItem(Long id) {
        Item item = getItemById(id);
        itemRepository.delete(item);
    }

    /**
     * Search items by term (searches name, locker number, and description)
     */
    @Transactional(readOnly = true)
    public List<Item> searchItems(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllItems();
        }
        return itemRepository.searchItems(searchTerm.trim());
    }
}
