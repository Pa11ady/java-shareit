package ru.practicum.shareit.requests.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostItemRequestDto {
    @NotBlank
    private String description;    //описание вещи;
}
