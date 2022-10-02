package ru.practicum.shareit.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchUserDto {
    private String name;        //имя или логин пользователя;
    private String email;       //адрес электронной почты (уникален)
}
