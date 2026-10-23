import java.time.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    public static final Duration MAX_SLOT = Duration.ofHours(3);

    private final IRoomRepository rooms;
    private final IBookingRepository bookings;
    private final Clock clock;
    private final List<BookingRule> rules;

    public BookingService(IRoomRepository rooms, IBookingRepository bookings, Clock clock) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.clock = clock;
        this.rules = List.of(
            new RoomMustExist(rooms),            // R1
            new SlotMustBeInFuture(clock),       // R2
            new SlotMustNotBeTooLong(MAX_SLOT),  // R3
            new RoomMustBeFree(bookings),        // R4
            new UserMustBeFree(bookings),        // R5
            new AtMostThreeActiveBookings(bookings, clock),  // D-1
            new SlotMustBeWithinFourteenDays(clock)          // D-2
        );
    }

    /** Book a room for a user. Returns the new booking. */
    public Booking book(String userId, String roomId, TimeSlot slot) {
        for (BookingRule rule : rules) {
            rule.check(userId, roomId, slot);
        }
        Booking booking = new Booking(userId, roomId, slot);
        bookings.save(booking);
        return booking;
    }

    /** Cancel a user's booking. */
    public void cancel(String userId, String roomId, TimeSlot slot) {
        Booking booking = new Booking(userId, roomId, slot);
        boolean exists = bookings.findByUser(userId).contains(booking);
        if (!exists) {
            throw new IllegalArgumentException("no such booking");
        }
        bookings.delete(booking);
    }

    /** A user's bookings, earliest first. */
    public List<Booking> bookingsFor(String userId) {
        return bookings.findByUser(userId).stream()
                .sorted(Comparator.comparing((Booking b) -> b.getSlot().getStart()))
                .collect(Collectors.toList());
    }

}
