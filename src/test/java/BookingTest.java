import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    private static TimeSlot slot(int startHour, int endHour) {
        return new TimeSlot(LocalDateTime.of(2027, 1, 11, startHour, 0),
                            LocalDateTime.of(2027, 1, 11, endHour, 0));
    }

    @Test
    void gettersReturnUserRoomAndSlot() {
        Booking booking = new Booking("alice", "R1", slot(10, 11));
        assertEquals("alice", booking.getUserId());
        assertEquals("R1", booking.getRoomId());
        assertEquals(slot(10, 11), booking.getSlot());
    }

    @Test
    void equalityUsesUserRoomAndSlot() {
        Booking booking = new Booking("alice", "R1", slot(10, 11));
        Booking same = new Booking("alice", "R1", slot(10, 11));
        assertEquals(booking, same);
        assertEquals(booking.hashCode(), same.hashCode());
        assertNotEquals(booking, new Booking("bob", "R1", slot(10, 11)));
        assertNotEquals(booking, new Booking("alice", "R2", slot(10, 11)));
        assertNotEquals(booking, new Booking("alice", "R1", slot(10, 12)));
        assertNotEquals(booking, null);
    }
}
