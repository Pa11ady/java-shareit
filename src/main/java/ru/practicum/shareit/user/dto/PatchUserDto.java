package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PatchUserDto {
    private String name;        //имя или логин пользователя;
    @Email
    private String email;       //адрес электронной почты (уникален)
}
