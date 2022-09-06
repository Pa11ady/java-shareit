package ru.practicum.shareit.item.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private  Long id;
    @NotBlank
    private  String text;           //содержимое комментария;
    private String authorName;      //автор комментария;
    private LocalDateTime created;  //дата создания
}
