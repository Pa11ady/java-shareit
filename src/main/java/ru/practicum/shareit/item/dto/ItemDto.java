package ru.practicum.shareit.item.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    @NotBlank
    private String name;            //краткое название
    @NotBlank
    private String description;     //развёрнутое описание

    @NotNull
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private Boolean available;      //доступна или нет вещь для аренды;
    private Long owner;             //владелец вещи;
    private Long request;           //если создано по запросу, то ссылка на запрос

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
