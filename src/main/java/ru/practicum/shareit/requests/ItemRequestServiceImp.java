package ru.practicum.shareit.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public ItemRequestDto create(Long userId, PostItemRequestDto postItemRequestDto) {
        User requester = UserMapper.mapToUser(userService.findById(userId));
        ItemRequest ItemRequest = ItemRequestMapper.mapToItemRequest(requester, postItemRequestDto,
                LocalDateTime.now());
        return ItemRequestMapper.mapToItemRequestDto(itemRequestRepository.save(ItemRequest));
    }

    @Override
    public List<ItemRequestDto> findAllByUserID(Long userId) {
        userService.findById(userId);
        List<ItemRequest> itemRequests = itemRequestRepository.findAllByRequesterId(userId, Sort.by("created"));
        return ItemRequestMapper.mapToItemRequestDto(itemRequests);
    }

    @Override
    public List<ItemRequestDto> findAll(Integer from, Integer size) {
        return null;
    }

    @Override
    public ItemRequestDto findById(Long requestId) {
        return null;
    }
}
