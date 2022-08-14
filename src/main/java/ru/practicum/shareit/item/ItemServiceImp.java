package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.PatchItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.UserService;

import javax.validation.ValidationException;
import java.util.List;

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
       /* if (itemDto.getAvailable() == null) {
            throw new ValidationException("review isPositive не заполнено!");
        }*/
        User owner = UserMapper.mapToUser(userService.findById(userId));
        Item item = itemRepository.create(ItemMapper.mapToItem(owner, null, itemDto));
        return ItemMapper.mapToItemDto(item);
    }

    @Override
    public ItemDto findById(Long itemId) {
        return null;
    }

    @Override
    public List<ItemDto> findAllByUserID(Long userId) {
        return null;
    }

    @Override
    public List<ItemDto> search(String text) {
        return null;
    }

    @Override
    public ItemDto update(Long userId, Long itemId, PatchItemDto patchItemDto) {
        return null;
    }
}
