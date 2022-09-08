package ru.practicum.shareit.item.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.requests.model.ItemRequest;
import ru.practicum.shareit.user.User;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "items", schema = "public")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String name;            //краткое название

    @Column(nullable = false)
    private String description;     //развёрнутое описание

    @Column(nullable = false)
    private boolean available;      //доступна или нет вещь для аренды

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;             //владелец вещи;

    @Transient
    private ItemRequest request;    //если создано по запросу, то ссылка на запрос

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
