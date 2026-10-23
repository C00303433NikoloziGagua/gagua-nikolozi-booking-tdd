import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    @Test
    void gettersReturnUserRoomAndSlot() {
        TimeSlot slot = new TimeSlot(LocalDateTime.of(2027, 1, 11, 10, 0),
                                     LocalDateTime.of(2027, 1, 11, 11, 0));
        Booking booking = new Booking("alice", "R1", slot);
        assertEquals("alice", booking.getUserId());
        assertEquals("R1", booking.getRoomId());
        assertEquals(slot, booking.getSlot());
    }
}
