package ru.practicum.shareit.requests;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.requests.dto.PostItemRequestDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {
    private final  ItemRequestService itemRequestService;

    @PostMapping
    public ItemRequestDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                                 @Valid  @RequestBody PostItemRequestDto itemRequest) {
        return itemRequestService.create(userId, itemRequest);
    }

    @GetMapping
    public List<ItemRequestDto> findAllByUserID(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemRequestService.findAllByUserID(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDto> findAll(@RequestHeader("X-Sharer-User-Id") Long userId,
                                 @RequestParam Integer from, @RequestParam Integer size) {
        return itemRequestService.findAll(from, size);
    }

    @GetMapping("/{requestId}")
    public ItemRequestDto findById(@PathVariable Long requestId) {
        return itemRequestService.findById(requestId);
    }

}
