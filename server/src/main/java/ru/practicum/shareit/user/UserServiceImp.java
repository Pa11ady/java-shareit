package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.common.exception.NotFoundException;
import ru.practicum.shareit.common.exception.UserAlreadyExistException;
import ru.practicum.shareit.user.dto.PatchUserDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        //checkEmail(userDto.getEmail());
        User user = userRepository.save(UserMapper.mapToUser(userDto));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.mapToUserDto(users);
    }

    @Transactional
    @Override
    public UserDto update(Long id, PatchUserDto patchUserDto) {
        User user = userRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        if (patchUserDto.getName() != null) {
            user.setName(patchUserDto.getName());
        }
        if (patchUserDto.getEmail() != null) {
            checkEmail(patchUserDto.getEmail());
            user.setEmail(patchUserDto.getEmail());
        }

        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    private void checkEmail(String email) {
      if (userRepository.existsByEmail(email)) {
            String message = "Пользователь с электронной почтой " + email + " уже зарегистрирован.";
            log.warn(message);
            throw new UserAlreadyExistException(message);
        }
    }

    private NotFoundException throwNotFoundException(Long id) {
        String message = "Пользователь с id " + id + " не найден!.";
        log.warn(message);
        return new NotFoundException(message);
    }
}
