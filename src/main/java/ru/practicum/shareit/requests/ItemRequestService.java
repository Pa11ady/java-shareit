package ru.practicum.shareit.requests;

import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.requests.dto.PostItemRequestDto;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDto create(Long userId, PostItemRequestDto postItemRequestDto);

    List<ItemRequestDto> findAllByUserID(Long userId);

    List<ItemRequestDto> findAll(Integer from, Integer size);

    ItemRequestDto findById(Long requestId);
}
