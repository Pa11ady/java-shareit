package ru.practicum.shareit.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.Valid;

/**
 * // TODO .
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto user) {
        //return service.create(user);
        return user;
    }


}
