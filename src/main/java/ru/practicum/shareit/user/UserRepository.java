package ru.practicum.shareit.user;

import ru.practicum.shareit.common.repository.CommonRepository;

public interface UserRepository extends CommonRepository<User> {
    boolean containsEmail(String email);
}
