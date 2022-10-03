package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingState;
import ru.practicum.shareit.booking.dto.PostBookingDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingClient bookingClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader("X-Sharer-User-Id") Long userId,
                                         @Valid @RequestBody PostBookingDto postBookingDto) {
        return bookingClient.create(userId, postBookingDto);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> findById(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId) {
        //Получение данных о конкретном бронировании
        return bookingClient.findById(userId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> findUserBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                            @RequestParam(defaultValue = "ALL", name = "state") String stateParam,
                                            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                            @Positive @RequestParam(defaultValue = "10") Integer size) {
        BookingState state = BookingState.from(stateParam)
                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));
        //получить бронирования текущего пользователя (его)
        return  bookingClient.findUserBooking(userId, state, from, size);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> findItemBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                            @RequestParam(defaultValue = "ALL", name = "state") String stateParam,
                                            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                            @Positive @RequestParam(defaultValue = "10") Integer size) {
        //все бронирования Вещей пользователя (другими)
        BookingState state = BookingState.from(stateParam)
               .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));
        return  bookingClient.findItemBooking(userId, state, from, size);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> approveRequest(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId,
                                  @RequestParam Boolean approved) {
        //Подтверждение или отклонение запроса на бронирование
        return  bookingClient.approveBooking(userId, bookingId, approved);
    }
}
