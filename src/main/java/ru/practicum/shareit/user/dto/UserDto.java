package ru.practicum.shareit.user.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;        //имя или логин пользователя;
    private String email;       //адрес электронной почты (уникален)
}
