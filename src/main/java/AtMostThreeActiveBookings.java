import java.time.Clock;
import java.time.LocalDateTime;

class AtMostThreeActiveBookings implements BookingRule {
    private final IBookingRepository bookings;
    private final Clock clock;

    AtMostThreeActiveBookings(IBookingRepository bookings, Clock clock) {
        this.bookings = bookings;
        this.clock = clock;
    }

    public void check(String userId, String roomId, TimeSlot slot) {
        LocalDateTime now = LocalDateTime.now(clock);
        int active = 0;
        for (Booking existing : bookings.findByUser(userId)) {
            if (existing.getSlot().getEnd().isAfter(now)) {
                active++;
            }
        }
        if (active >= 3) {
            throw new IllegalStateException("user already holds 3 bookings that have not ended");
        }
    }
}
