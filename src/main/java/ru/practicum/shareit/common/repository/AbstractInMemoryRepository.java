package ru.practicum.shareit.common.repository;

import ru.practicum.shareit.common.AbstractEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractInMemoryRepository<E extends AbstractEntity> implements CommonRepository<E> {
    private final Map<Long, E> storage = new HashMap<>();
    private long currentId = 0;

    @Override
    public E create(E data) {
        data.setId(++currentId);
        storage.put(data.getId(), data);
        return  data;
    }

    @Override
    public E findById(Long id) {
        return storage.get(id);
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public E update(E data) {
        storage.put(data.getId(), data);
        return data;
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }
}
