package ru.practicum.shareit.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;        //имя или логин пользователя;
    @Email
    @NotBlank
    private String email;       //адрес электронной почты (уникален)
}
