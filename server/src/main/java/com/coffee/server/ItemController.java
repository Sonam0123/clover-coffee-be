package com.coffee.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ItemController {
    private List<Item> items = new ArrayList<>(Arrays.asList(
            new Item(1, "Item 1", "Description 1", 100),
            new Item(2, "Item 2", "Description 2", 200),
            new Item(3, "Item 3", "Description 3", 300)));

    @GetMapping("/items")
    @ResponseBody
    public List<Item> getItems() {
        return items;
    }

    @GetMapping("/items/{id}")
    @ResponseBody
    public Item getItemById(@PathVariable int id) {
        return items.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    @PostMapping("/items")
    public ResponseEntity<Item> addItem(@Valid @RequestBody Item newItem) {
        int newId = items.size() + 1;
        newItem.setId(newId);
        items.add(newItem);
        return ResponseEntity.ok(newItem);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @Valid @NotNull @RequestBody Item updatedItem) {
        Item existingItem = items.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        if (existingItem == null) {
            return ResponseEntity.notFound().build();
        }
        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setPrice(updatedItem.getPrice());
        return ResponseEntity.ok(existingItem);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        Item itemToDelete = items.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        if (itemToDelete != null) {
            items.remove(itemToDelete);
            String message = "Item " + id + " has been deleted!";
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.notFound().build();
    }

    

}
