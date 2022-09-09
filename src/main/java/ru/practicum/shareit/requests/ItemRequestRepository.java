package ru.practicum.shareit.requests;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.requests.model.ItemRequest;

import java.util.List;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {
    List<ItemRequest> findAllByRequesterId(Long RequesterId, Sort sort);

    List<ItemRequest> findAllByRequesterIdNot(Long RequesterId, Pageable pageable);
}
