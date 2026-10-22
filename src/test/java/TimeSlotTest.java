import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {
    private static final LocalDateTime NINE = LocalDateTime.of(2027, 1, 11, 9, 0);
    private static final LocalDateTime TEN = LocalDateTime.of(2027, 1, 11, 10, 0);
    private static final LocalDateTime ELEVEN = LocalDateTime.of(2027, 1, 11, 11, 0);

    @Test
    void endBeforeStartIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(TEN, NINE));
    }

    @Test
    void endEqualToStartIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(TEN, TEN));
    }

    @Test
    void nullStartIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(null, TEN));
    }

    @Test
    void nullEndIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(TEN, null));
    }

    @Test
    void gettersReturnStartAndEnd() {
        TimeSlot slot = new TimeSlot(TEN, ELEVEN);
        assertEquals(TEN, slot.getStart());
        assertEquals(ELEVEN, slot.getEnd());
    }
}
