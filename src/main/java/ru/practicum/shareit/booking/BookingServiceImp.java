package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.PostBookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.common.exception.BookingAlreadyApproveException;
import ru.practicum.shareit.common.exception.IncorrectDateException;
import ru.practicum.shareit.common.exception.ItemNotAvailableException;
import ru.practicum.shareit.common.exception.NotFoundException;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public BookingDto create(Long userId, PostBookingDto postBookingDto) {
        User booker = UserMapper.mapToUser(userService.findById(userId));
        Long itemId = postBookingDto.getItemId();
        Item item = itemRepository.findById(itemId).orElseThrow(() -> throwNotFoundItemException("Предмет с id " +
                itemId + " не найден!"));
        checkItemAvailable(item);
        Booking booking = BookingMapper.mapToBooking(booker, item, postBookingDto, BookingStatus.WAITING);
        checkDate(booking);
        return BookingMapper.mapToBookingDto(bookingRepository.save(booking));
    }

    @Override
    public BookingDto findById(Long userId, Long bookingId) {
        return null;
    }

    @Override
    public List<BookingDto> findUserBooking(Long userId, String stateParam) {
        return null;
    }

    @Override
    public List<BookingDto> findAllByUserID(Long userId, String stateParam) {
        return null;
    }

    @Override
    public BookingDto approveBooking(Long userId, Long bookingId, Boolean approved) {
        User user = UserMapper.mapToUser(userService.findById(userId));
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> throwNotFoundItemException(
                "Бронирование с id " + bookingId + " не найдено!"));
        itemService.checkPermissions(userId, booking.getItem());
        checkStatus(booking);
        BookingStatus status = (approved ? BookingStatus.APPROVED : BookingStatus.REJECTED);
        booking.setStatus(status);


        return BookingMapper.mapToBookingDto(bookingRepository.save(booking));
    }

    private NotFoundException throwNotFoundItemException(String message) {
        log.warn(message);
        throw new NotFoundException(message);
    }

    private void checkItemAvailable(Item item) {
        if (!item.isAvailable()) {
            String message = ("Товар " + item.getId() + " не доступен для бронирования");
            log.warn(message);
            throw new ItemNotAvailableException(message);
        }
    }

    private void checkDate(Booking booking) {
        if (booking.getStart().isBefore(LocalDateTime.now()) || booking.getStart().isAfter(booking.getEnd())) {
            String message = ("Некорректная дата бронирования");
            log.warn(message);
            throw new IncorrectDateException(message);
        }
    }

    private void checkStatus(Booking booking) {
        if (!booking.getStatus().equals(BookingStatus.WAITING)) {
            String message = ("Бронирование уже одобрено");
            log.warn(message);
            throw new BookingAlreadyApproveException(message);
        }
    }
}
