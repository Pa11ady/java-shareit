package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.PostBookingDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                             @Valid @RequestBody PostBookingDto postBookingDto) {
        return bookingService.create(userId, postBookingDto);
    }

    @GetMapping("/{bookingId}")
    public BookingDto findById(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId) {
        //Получение данных о конкретном бронировании
        return bookingService.findById(userId, bookingId);
    }

    @GetMapping
    public List<BookingDto> findUserBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                            @RequestParam(defaultValue = "ALL", name = "state") String stateParam,
                                            @RequestParam(defaultValue = "0") Integer from,
                                            @RequestParam(defaultValue = "10") Integer size) {
        //получить бронирования текущего пользователя (его)
        return bookingService.findUserBooking(userId, stateParam, from, size);
    }

   @GetMapping("/owner")
    public List<BookingDto> findItemBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                            @RequestParam(defaultValue = "ALL", name = "state") String stateParam,
                                            @RequestParam(defaultValue = "0") Integer from,
                                            @RequestParam(defaultValue = "10") Integer size) {
        //все бронирования Вещей пользователя (другими)
        return bookingService.findItemBooking(userId, stateParam, from, size);
    }

    @PatchMapping("/{bookingId}")
    public  BookingDto approveRequest(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId,
                                  @RequestParam Boolean approved) {
        //Подтверждение или отклонение запроса на бронирование
        return bookingService.approveBooking(userId, bookingId, approved);
    }
}
