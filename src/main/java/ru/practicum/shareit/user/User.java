package ru.practicum.shareit.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;        //имя или логин пользователя;
    private String email;       //адрес электронной почты (уникален)
}
