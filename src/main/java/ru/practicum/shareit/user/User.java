package ru.practicum.shareit.user;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.common.AbstractEntity;

@Getter
@Setter
public class User extends AbstractEntity {
    private String name;        //имя или логин пользователя;
    private String email;       //адрес электронной почты (уникален)
}
