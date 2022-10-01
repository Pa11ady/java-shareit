package ru.practicum.shareit.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.common.OffsetPage;
import ru.practicum.shareit.common.exception.NotFoundException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.requests.dto.PostItemRequestDto;
import ru.practicum.shareit.requests.model.ItemRequest;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemRequestServiceImp implements ItemRequestService {
    private final UserService userService;
    private final ItemRequestRepository itemRequestRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public ItemRequestDto create(Long userId, PostItemRequestDto postItemRequestDto) {
        User requester = UserMapper.mapToUser(userService.findById(userId));
        ItemRequest itemRequest = ItemRequestMapper.mapToItemRequest(requester, postItemRequestDto,
                LocalDateTime.now());
        return ItemRequestMapper.mapToItemRequestDto(itemRequestRepository.save(itemRequest));
    }

    @Override
    public List<ItemRequestDto> findAllByUserID(Long userId) {
        userService.findById(userId);
        List<ItemRequest> itemRequests = itemRequestRepository.findAllByRequesterId(userId, Sort.by("created"));
        List<ItemRequestDto> dtoItemRequests = ItemRequestMapper.mapToItemRequestDto(itemRequests);
        dtoItemRequests.forEach(this::loadItems);
        return dtoItemRequests;
    }

    @Override
    public List<ItemRequestDto> findAll(Long userId, Integer from, Integer size) {
        Pageable pageable = new OffsetPage(from, size, Sort.by("created"));
        List<ItemRequest> itemRequests = itemRequestRepository.findAllByRequesterIdNot(userId, pageable);
        List<ItemRequestDto> dtoItemRequests = ItemRequestMapper.mapToItemRequestDto(itemRequests);
        dtoItemRequests.forEach(this::loadItems);
        return dtoItemRequests;
    }

    @Override
    public ItemRequestDto findById(Long userId, Long requestId) {
        userService.findById(userId);
        ItemRequest itemRequests = itemRequestRepository.findById(requestId).orElseThrow(() ->
                throwNotFoundException(requestId));
        ItemRequestDto itemRequestDto = ItemRequestMapper.mapToItemRequestDto(itemRequests);
        loadItems(itemRequestDto);
        return itemRequestDto;
    }

    private void loadItems(ItemRequestDto itemRequestDto) {
        List<Item> items = itemRepository.findAllByRequestId(itemRequestDto.getId());
        itemRequestDto.setItems(ItemMapper.mapToItemDto(items));
    }

    private NotFoundException throwNotFoundException(Long id) {
        String message = "Запрос с id " + id + " не найден!";
        log.warn(message);
        return new NotFoundException(message);
    }
}
