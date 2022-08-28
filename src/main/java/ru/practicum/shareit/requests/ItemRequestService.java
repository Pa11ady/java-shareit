package ru.practicum.shareit.requests;

import ru.practicum.shareit.requests.dto.ItemRequestDto;

public interface ItemRequestService {

    ItemRequestDto create(ItemRequestDto requestDto);
}
