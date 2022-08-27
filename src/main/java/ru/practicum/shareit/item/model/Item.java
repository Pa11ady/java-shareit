package ru.practicum.shareit.item.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.requests.ItemRequest;
import ru.practicum.shareit.user.User;

import javax.persistence.*;

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
    @JoinColumn(name="user_id", nullable = false)
    private User owner;             //владелец вещи;

    @Transient
    private ItemRequest request;    //если создано по запросу, то ссылка на запрос

    public Item() {
    }

    public Item(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.available = item.isAvailable();
        this.owner = item.getOwner();
        this.request = item.getRequest();
    }
}
