package com.example.CadetInventoryManagementSystem1.exception;

public class ItemNotFoundException extends RuntimeException {
    
    public ItemNotFoundException(Long id) {
        super("Item not found with id: " + id);
    }
    
    public ItemNotFoundException(String message) {
        super(message);
    }
}
