package ru.practicum.shareit.requests;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.requests.dto.PostItemRequestDto;
import ru.practicum.shareit.requests.model.ItemRequest;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemRequestMapper {
    public static ItemRequestDto mapToItemRequestDto(ItemRequest itemRequest) {
        UserDto requesterDto = UserMapper.mapToUserDto(itemRequest.getRequester());
        return new ItemRequestDto(
                itemRequest.getId(),
                itemRequest.getDescription(),
                requesterDto,
                itemRequest.getCreate()
        );
    }

    public static List<ItemRequestDto> mapToItemRequestDto(Iterable<ItemRequest> itemRequests) {
        List<ItemRequestDto> result = new ArrayList<>();

        for (var el : itemRequests) {
            result.add(mapToItemRequestDto(el));
        }

        return result;
    }

    public static ItemRequest mapToItemRequest(User requester, PostItemRequestDto postItemRequestDto,
                                               LocalDateTime date) {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setDescription(postItemRequestDto.getDescription());
        itemRequest.setRequester(requester);
        itemRequest.setCreate(date);
        return  itemRequest;
    }
}
