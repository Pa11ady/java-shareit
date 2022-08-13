package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto user);

    UserDto findById(Long id);

    List<UserDto> findAll();

    UserDto update(Long id, UserDto user);

    void delete(Long id);
}
