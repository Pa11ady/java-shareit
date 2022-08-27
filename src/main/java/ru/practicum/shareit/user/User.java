package ru.practicum.shareit.user;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.common.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;        //имя или логин пользователя;

    @Column(nullable = false)
    private String email;       //адрес электронной почты (уникален)

    public  User() {
    }

    public User(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
    }
}
