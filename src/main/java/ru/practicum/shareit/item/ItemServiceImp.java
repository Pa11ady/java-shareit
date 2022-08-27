package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.common.exception.NotFoundException;
import ru.practicum.shareit.common.exception.PermissionException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.PatchItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ItemServiceImp implements ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;

    @Autowired
    public ItemServiceImp(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @Override
    public ItemDto create(Long userId, ItemDto itemDto) {
        User owner = UserMapper.mapToUser(userService.findById(userId));
        Item item = itemRepository.save(ItemMapper.mapToItem(owner, null, itemDto));
        return ItemMapper.mapToItemDto(item);
    }

    @Override
    public ItemDto findById(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> throwNotFoundException(itemId));
        return ItemMapper.mapToItemDto(item);
    }

    @Override
    public List<ItemDto> findAllByUserID(Long userId) {
        userService.findById(userId);   //исключение, если пользователь не найден
        List<Item> items = itemRepository.findAllByOwnerId(userId);
        return ItemMapper.mapToItemDto(items);
    }

    @Override
    public List<ItemDto> search(String text) {
        if (text.isBlank()) {
            return Collections.emptyList();
        }
       List<Item> items = itemRepository.search(text);

       return ItemMapper.mapToItemDto(items);
    }

    @Override
    public ItemDto update(Long userId, Long itemId, PatchItemDto patchItemDto) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> throwNotFoundException(itemId));
        checkPermissions(userId, item);

        if (patchItemDto.getName() != null) {
            item.setName(patchItemDto.getName());
        }
        if (patchItemDto.getDescription() != null) {
            item.setDescription(patchItemDto.getDescription());
        }
        if (patchItemDto.getAvailable() != null) {
            item.setAvailable(patchItemDto.getAvailable());
        }

        return ItemMapper.mapToItemDto(itemRepository.save(item));
    }

    private void checkPermissions(Long userId, Item item) {
        if (!Objects.equals(userId, item.getOwner().getId())) {
            String message = ("Пользователь с id " +
                    userId + " не владелец предмета с id " + item.getId());
            log.warn(message);
            throw new PermissionException(message);
        }
    }

    private NotFoundException throwNotFoundException(Long id) {
        String message = ("Предмет с id " + id + " не найден!");
        log.warn(message);
        throw new NotFoundException(message);
    }
}
