package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.common.exception.UserAlreadyExistException;
import ru.practicum.shareit.user.dto.PatchUserDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserServiceTest {
    private final UserService userService;
    private UserDto userDto1;
    private UserDto userDto2;

    @BeforeEach
    void setUp() {
        userDto1 = new UserDto(1L, "User1", "user1@mail.ru");
        userDto2 = new UserDto(2L, "User2", "user2@mail.ru");
    }

    @Test
    void shouldReturnUserWhenGetUserById() {
        UserDto returnUserDto = userService.create(userDto1);
        assertThat(returnUserDto.getName(), equalTo(userDto1.getName()));
        assertThat(returnUserDto.getEmail(), equalTo(userDto1.getEmail()));
    }

    @Test
    void shouldDoesNotThrowWhenDeleteUserWithWrongId() {
        assertDoesNotThrow(() -> userService.delete(100500L));
    }

    @Test
    void shouldDeleteUser() {
        UserDto tmpUserDto = new UserDto(100L, "tmp", "tmp@tmp.com");
        UserDto returnUserDto = userService.create(tmpUserDto);
        List<UserDto> listUser = userService.findAll();
        int size = listUser.size();
        userService.delete(returnUserDto.getId());
        listUser = userService.findAll();
        assertThat(listUser.size(), equalTo(size - 1));
    }

    @Test
    void shouldUpdateUser() {
        UserDto returnUserDto = userService.create(userDto1);
        PatchUserDto patchUserDto = new PatchUserDto();
        patchUserDto.setName("NewName");
        patchUserDto.setEmail("new@email.ru");
        userService.update(returnUserDto.getId(), patchUserDto);
        UserDto updateUserDto = userService.findById(returnUserDto.getId());
        assertThat(updateUserDto.getName(), equalTo("NewName"));
        assertThat(updateUserDto.getEmail(), equalTo("new@email.ru"));
    }

    @Test
    void shouldExceptionWhenUpdateUserWithExistEmail() {
        UserDto returnUserDto1 = userService.create(userDto1);
        UserDto returnUserDto2 = userService.create(userDto2);
        PatchUserDto patchUserDto = new PatchUserDto();
        patchUserDto.setEmail(userDto1.getEmail());
        UserAlreadyExistException exp = Assertions.assertThrows(
                UserAlreadyExistException.class,
                () -> userService.update(returnUserDto2.getId(), patchUserDto));
        assertFalse(exp.getMessage().isEmpty());
    }
}

