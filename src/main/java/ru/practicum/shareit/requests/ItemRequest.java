package ru.practicum.shareit.requests;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemRequest {
    private Long id;
    private String description;    //описание вещи;
    private User requestor;        //пользователь, создавший запрос
    private LocalDateTime create;  //дата и время создания запроса
}
