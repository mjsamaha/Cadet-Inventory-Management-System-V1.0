package com.example.CadetInventoryManagementSystem1.controller;

import com.example.CadetInventoryManagementSystem1.entity.Item;
import com.example.CadetInventoryManagementSystem1.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Create a new item
     * POST /api/items
     */
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    /**
     * Get all items
     * GET /api/items
     */
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    /**
     * Get item by ID
     * GET /api/items/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    /**
     * Update an existing item
     * PUT /api/items/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Item updatedItem = itemService.updateItem(id, itemDetails);
        return ResponseEntity.ok(updatedItem);
    }

    /**
     * Delete an item
     * DELETE /api/items/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Search items by term
     * GET /api/items/search?query={searchTerm}
     */
    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(@RequestParam(required = false) String query) {
        List<Item> items = itemService.searchItems(query);
        return ResponseEntity.ok(items);
    }
}
