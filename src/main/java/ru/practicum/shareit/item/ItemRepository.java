package ru.practicum.shareit.item;

import ru.practicum.shareit.common.repository.CommonRepository;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository extends CommonRepository<Item> {
    public List<Item> findAllByUserID(Long userId);

    List<Item> search(String text);
}
