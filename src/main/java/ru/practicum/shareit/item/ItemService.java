package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.PatchItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    ItemDto create(Long userId, ItemDto itemDto);

    ItemDto findById(Long itemId);

    List<ItemDto> findAllByUserID(Long userId);

    List<ItemDto> search(String text);

    ItemDto update(Long userId, Long itemId, PatchItemDto patchItemDto);

    public void checkPermissions(Long userId, Item item);
}
