package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.common.repository.AbstractInMemoryRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Component
public class InMemoryItemRepositoryImp extends AbstractInMemoryRepository<Item> implements ItemRepository {

    @Override
    public Item findById(Long id) {
        Item item = super.findById(id);
        if (item == null) {
            return null;
        }
        return new Item(item);
    }

    @Override
    public List<ItemDto> findAllByUserID(Long userId) {
        return null;
    }

    @Override
    public List<ItemDto> search(String text) {
        return null;
    }
}
