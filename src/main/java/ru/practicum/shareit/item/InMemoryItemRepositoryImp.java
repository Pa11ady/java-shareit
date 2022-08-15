package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.common.repository.AbstractInMemoryRepository;
import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Item> findAllByUserID(Long userId) {
        return super.findAll().stream()
                .filter(x -> x.getOwner().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> search(String text) {
        return null;
    }
}
