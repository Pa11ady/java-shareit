package ru.practicum.shareit.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class PatchUserDto {
    private String name;        //имя или логин пользователя;
    @Email
    private String email;       //адрес электронной почты (уникален)
}
