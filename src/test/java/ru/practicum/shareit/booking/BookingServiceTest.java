package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.PostBookingDto;
import ru.practicum.shareit.common.exception.BookingAlreadyApproveException;
import ru.practicum.shareit.common.exception.IncorrectDateException;
import ru.practicum.shareit.common.exception.ItemNotAvailableException;
import ru.practicum.shareit.common.exception.PermissionException;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.PatchItemDto;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BookingServiceTest {
    private final BookingService bookingService;
    private final UserService userService;
    private final ItemService itemService;
    private UserDto userDto1;
    private UserDto userDto2;
    private ItemDto itemDto1;

    @BeforeEach
    void setUp() {
        userDto1 = new UserDto(101L,
                "test1",
                "test1@mail.ru");
        userDto2 = new UserDto(101L,
                "test2",
                "test2@mail.ru");
        itemDto1 = new ItemDto(
                101L,
                "item1",
                "item1 description",
                true,
                200L,
                null,
                null,
                null,
                null
        );
    }

    @Test
    void shouldExceptionWhenCreateBookingByOwner() {
        UserDto ownerDto = userService.create(userDto1);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2027, 1, 5, 2, 0, 0),
                LocalDateTime.of(2027, 1, 6, 2, 0, 0),
                newItemDto.getId());
        //???????????????????? ?????? ???????????????????????? ????????????????????
        PermissionException exp = assertThrows(PermissionException.class,
                () -> bookingService.create(ownerDto.getId(), postBookingDto));
        assertFalse(exp.getMessage().isEmpty());
    }

    @Test
    void shouldExceptionWhenIncorrectDate() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(1990, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        IncorrectDateException exp = assertThrows(IncorrectDateException.class,
                () -> bookingService.create(ownerDto.getId(), postBookingDto));
        assertFalse(exp.getMessage().isEmpty());
    }

    @Test
    void shouldExceptionWhenItemNotAvailableException() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PatchItemDto patchItemDto = new PatchItemDto(newItemDto.getName(), newItemDto.getDescription(), false);
        newItemDto = itemService.update(ownerDto.getId(), newItemDto.getId(), patchItemDto);

        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        ItemNotAvailableException exp = assertThrows(ItemNotAvailableException.class,
                () -> bookingService.create(ownerDto.getId(), postBookingDto));
        assertFalse(exp.getMessage().isEmpty());
    }

    @Test
    void shouldExceptionWhenGetBookingByNotOwnerOrNotBooker() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        UserDto userDto3 = new UserDto(203L, "user3", "user3@mail.ru");
        userDto3 = userService.create(userDto3);
        Long userId = userDto3.getId();
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2027, 1, 5, 2, 0, 0),
                LocalDateTime.of(2027, 1, 6, 2, 0, 0),
                newItemDto.getId());

        BookingDto bookingDto = bookingService.create(newUserDto.getId(), postBookingDto);
        //???????????????????? ???????????????????????? ?????????? ???????????? ???????????????? ???????? ?????? ??????????????????????
        PermissionException exp = assertThrows(PermissionException.class,
                () -> bookingService.findById(userId, bookingDto.getId()));
        assertFalse(exp.getMessage().isEmpty());
    }

  @Test
    void shouldReturnBookingsWhenGetBookingsByBookerAndSizeIsNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findUserBooking(newUserDto.getId(), "ALL", 0, 10);
        assertEquals(2, listBookings.size());
    }

    @Test
    void shouldReturnBookingsWhenGetBookingsByBookerAndSizeIsNotNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findUserBooking(newUserDto.getId(), "ALL", 0, 1);
        assertEquals(1, listBookings.size());
    }


    @Test
    void shouldReturnBookingsWhenGetBookingsInWaitingStatusByBookerAndSizeIsNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findUserBooking(newUserDto.getId(), "WAITING", 0, 10);
        assertEquals(2, listBookings.size());
    }


    @Test
    void shouldReturnBookingsWhenGetBookingsInWaitingStatusByBookerAndSizeNotNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findUserBooking(newUserDto.getId(), "WAITING", 0, 1);
        assertEquals(1, listBookings.size());
    }


    @Test
    void shouldReturnBookingsWhenGetBookingsInRejectedStatusByBookerAndSizeIsNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findUserBooking(newUserDto.getId(), "REJECTED", 0, 10);
        assertEquals(0, listBookings.size());
    }

    @Test
    void shouldReturnBookingsWhenGetBookingsInRejectedStatusByBookerAndSizeNotNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findUserBooking(newUserDto.getId(), "REJECTED", 0, 1);
        assertEquals(0, listBookings.size());
    }

    @Test
    void shouldReturnBookingsWhenGetBookingsByOwnerAndSizeIsNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findItemBooking(ownerDto.getId(), "ALL", 0, 10);
        assertEquals(2, listBookings.size());
    }

    @Test
    void shouldReturnBookingsWhenGetBookingsByOwnerAndSizeNotNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findItemBooking(ownerDto.getId(), "ALL", 0, 1);
        assertEquals(1, listBookings.size());
    }

    @Test
    void shouldReturnBookingsWhenGetBookingsByOwnerAndStatusWaiting() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findItemBooking(ownerDto.getId(), "WAITING", 0, 10);
        assertEquals(2, listBookings.size());
    }

    @Test
    void shouldReturnBookingsWhenGetBookingsByOwnerAndStatusWaitingAndSizeNotNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findItemBooking(ownerDto.getId(), "WAITING", 0, 1);
        assertEquals(1, listBookings.size());
    }

    @Test
    void shouldReturnBookingsWhenGetBookingsByOwnerAndStatusRejectedAndSizeIsNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findItemBooking(ownerDto.getId(), "REJECTED", 0, 10);
        assertEquals(0, listBookings.size());
    }

    @Test
    void shouldReturnBookingsWhenGetBookingsByOwnerAndStatusRejectedAndSizeNotNull() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        bookingService.create(newUserDto.getId(), postBookingDto);
        PostBookingDto bookingInputDto1 = new PostBookingDto(
                LocalDateTime.of(2026, 12, 20, 1, 0, 0),
                LocalDateTime.of(2026, 12, 30, 11, 0, 0),
                newItemDto.getId());
        bookingService.create(newUserDto.getId(), bookingInputDto1);
        List<BookingDto> listBookings = bookingService.findItemBooking(ownerDto.getId(), "REJECTED", 0, 1);
        assertEquals(0, listBookings.size());
    }

    @Test
    void  shouldExceptionWhenBookingAlreadyApprove() {
        UserDto ownerDto = userService.create(userDto1);
        UserDto newUserDto = userService.create(userDto2);
        ItemDto newItemDto = itemService.create(ownerDto.getId(), itemDto1);
        PostBookingDto postBookingDto = new PostBookingDto(
                LocalDateTime.of(2025, 1, 5, 2, 0, 0),
                LocalDateTime.of(2025, 1, 20, 10, 0, 0),
                newItemDto.getId()
        );
        BookingDto result = bookingService.create(newUserDto.getId(), postBookingDto);
        bookingService.approveBooking(ownerDto.getId(), result.getId(), true);
        BookingAlreadyApproveException exp = assertThrows(BookingAlreadyApproveException.class,
                () -> bookingService.approveBooking(ownerDto.getId(), result.getId(), true));
        assertFalse(exp.getMessage().isEmpty());
    }
}