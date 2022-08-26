package ru.practicum.shareit.common.repository;

import ru.practicum.shareit.common.AbstractEntity;

import java.util.List;

public interface CommonRepository<E extends AbstractEntity> {
    E create(E data);

    E findById(Long id);

    List<E> findAll();

    E update(E data);

    void delete(Long id);
}
