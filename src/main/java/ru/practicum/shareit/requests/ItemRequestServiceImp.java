package ru.practicum.shareit.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.requests.dto.PostItemRequestDto;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemRequestServiceImp implements ItemRequestService {
    @Override
    public ItemRequestDto create(Long userId, PostItemRequestDto postItemRequestDto) {
        return null;
    }

    @Override
    public List<ItemRequestDto> findAllByUserID(Long userId) {
        return null;
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
