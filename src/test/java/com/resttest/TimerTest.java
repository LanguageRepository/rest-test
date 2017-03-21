package com.resttest;

import com.resttest.items.Item;
import com.resttest.items.ItemDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Владислав on 18.03.2017.
 */
public class TimerTest {

    public static void main(String[] args) {

        HashSet<Item> entities = new HashSet<>();
        entities.add(new Item(6l, "Vlad"));
        entities.add(new Item(5l, "Alex"));
        entities.add(new Item(4l, "Grisha"));
        HashSet<ItemDto> dtos = new HashSet<>();
        dtos.add(new ItemDto(6l, "Vlad"));
        dtos.add(new ItemDto(5l, "Igor"));
        dtos.add(new ItemDto(8l, "Pasha"));
        entities.retainAll(new TimerTest().convertDtosToEntities(new ArrayList<>(dtos)));
        entities.forEach(System.out::println);
    }

    private List<Item> convertDtosToEntities(List<ItemDto> dtos) {
        List<Item> entities = new ArrayList<>();
        for (ItemDto dto : dtos) {
            Item entity = new Item(null, null);
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entities.add(entity);
        }
        return entities;
    }

}
