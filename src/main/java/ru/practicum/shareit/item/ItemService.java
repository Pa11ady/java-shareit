package ru.practicum.shareit.item;

import ru.practicum.shareit.item.comment.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.PatchItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    ItemDto create(Long userId, ItemDto itemDto);

    ItemDto findByItemId(Long userId, Long itemId);

    List<ItemDto> findAllByUserID(Long userId, Integer from, Integer size);

    List<ItemDto> findAllByUserID(Long userId);

    List<ItemDto> search(String text);

    List<ItemDto> search(String text, Integer from, Integer size);

    ItemDto update(Long userId, Long itemId, PatchItemDto patchItemDto);

    void checkPermissions(Long userId, Item item);

    CommentDto createComment(Long userId, Long itemId, CommentDto commentDto);
}
