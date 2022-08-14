package ru.practicum.shareit.item;

import ru.practicum.shareit.common.repository.CommonRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.util.List;

public interface ItemRepository extends CommonRepository<Item> {
    public List<ItemDto> findAllByUserID(Long userId);

    List<ItemDto> search(String text);
}
