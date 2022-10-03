package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchItemDto {
    private String name;            //краткое название
    private String description;     //развёрнутое описание
    private Boolean available;      //доступна или нет вещь для аренды;
}
