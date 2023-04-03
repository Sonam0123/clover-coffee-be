package com.coffee.server;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coffee.server.Item;

@RestController
public class ItemController {
    private List<Item> items = Arrays.asList(
            new Item("Item 1", "Description 1", 100),
            new Item("Item 2", "Description 2", 200),
            new Item("Item 3", "Description 3", 300));

    @GetMapping("/items")
    @ResponseBody
    public List<Item> getItems() {
        return items;
    }
}
