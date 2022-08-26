package ru.practicum.shareit.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchItemDto {
    private String name;            //краткое название
    private String description;     //развёрнутое описание
    private Boolean available;      //доступна или нет вещь для аренды;
}
