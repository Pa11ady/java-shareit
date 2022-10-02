package ru.practicum.shareit.requests.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "requests", schema = "public")
public class ItemRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @Column(nullable = false)
    private String description;    //описание вещи;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User requester;        //пользователь, создавший запрос

    @Column(nullable = false)
    private LocalDateTime created;  //дата и время создания запроса
}
