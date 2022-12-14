package ru.practicum.shareit.item.comment.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments", schema = "public")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private  Long id;

    @Column(nullable = false)
    private  String text;           //содержимое комментария;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;              //вещь, к которой относится комментарий;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;            //автор комментария;

    @Column(nullable = false)
    private LocalDateTime created;  //дата создания
}
