package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.common.exception.UserAlreadyExistException;
import ru.practicum.shareit.user.dto.PatchUserDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

@Slf4j
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserDto userDto) {
        checkEmail(userDto.getEmail());
        User user = userRepository.create(UserMapper.mapToUser(userDto));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.mapToUserDto(users);
    }

    @Override
    public UserDto update(Long id, PatchUserDto patchUserDto) {
        User user = userRepository.findById(id);
        if (patchUserDto.getName() != null) {
            user.setName(patchUserDto.getName());
        }
        if (patchUserDto.getEmail() != null) {
            checkEmail(patchUserDto.getEmail());
            user.setEmail(patchUserDto.getEmail());
        }

        return UserMapper.mapToUserDto(userRepository.update(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    private void checkEmail(String email) {
        if (userRepository.containsEmail(email)) {
            String message = ("Пользователь с электронной почтой " +
                    email + " уже зарегистрирован.");
            log.warn(message);
            throw new UserAlreadyExistException(message);
        }
    }
}
