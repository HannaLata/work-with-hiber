package com.hannalata.dao;

import com.hannalata.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

    ItemDAO itemDAO = new ItemDAO();
    List<Item> items;
    @BeforeEach
    void setUp() {
       items = new ArrayList<>();
        Item item = new Item("itemCode", "item name",
                12345, 5);
        items.add(item);
    }

    @AfterEach
    void tearDown() {
        items.forEach(it -> itemDAO.delete(it));
    }

    @Test
    void saveAndGetAndDelete() {

        Item savedItem = itemDAO.save(items.get(0));
        assertNotNull(savedItem);
        assertNotNull(savedItem.getId());

        Item retrievedItem = itemDAO.findOne(savedItem.getId());
        assertNotNull(retrievedItem);
        assertEquals("itemCode", retrievedItem.getCode());


    }
}