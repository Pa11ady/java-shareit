package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.PatchItemDto;

@Service
public class ItemClient extends BaseClient {
    private static final String API_PREFIX = "/items";

    @Autowired
    public ItemClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> create(Long userId, ItemDto item) {
        return post("", userId, item);
    }

    public ResponseEntity<Object> findByItemId(Long userId, Long itemId) {
        return get("/" + itemId, userId);
    }

    public ResponseEntity<Object> findAllByUserID(Long userId, Integer from, Integer size) {
        String path = "?from=" + from + "&size=" + size;
        return get(path, userId);
    }

    public ResponseEntity<Object> search(String text, Integer from, Integer size) {
        String path = "/search?text=" + text + "&from=" + from + "&size=" + size;
        return get(path);
    }

    public ResponseEntity<Object> update(Long userId, Long itemId, PatchItemDto item) {
        return  patch("/" + itemId, userId, item);
    }

    public ResponseEntity<Object> createComment(Long userId, Long itemId, CommentDto commentDto) {
        return post("/" + itemId + "/comment", userId, commentDto);
    }
}