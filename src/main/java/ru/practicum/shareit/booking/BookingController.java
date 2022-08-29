package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingDto create(@Valid @RequestBody BookingDto bookingDto) {
        return bookingService.create(bookingDto);
    }

    @GetMapping("/{bookingId}")
    public BookingDto findById(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId) {
        //Получение данных о конкретном бронировании
        return bookingService.findById(userId, bookingId);
    }

    @GetMapping
    public List<BookingDto> findUserBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                         @RequestParam String stateParam) {
        //получить бронирования текущего пользователя (его)
        return bookingService.findUserBooking(userId, stateParam);
    }

    @GetMapping("/owner")
    public List<BookingDto> findItemBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                         @RequestParam(defaultValue = "ALL") String stateParam) {
        //все бронирования Вещей пользователя (другими)
        return bookingService.findAllByUserID(userId, stateParam);
    }

    @PatchMapping("/{bookingId}")
    public  BookingDto approveRequest(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId,
                                  @RequestParam(defaultValue = "ALL") Boolean approved) {
        //Подтверждение или отклонение запроса на бронирование
        return bookingService.approveBooking(userId, bookingId, approved);
    }


}
