import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.Duration;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {
    private static final LocalDateTime NINE = LocalDateTime.of(2027, 1, 11, 9, 0);
    private static final LocalDateTime TEN = LocalDateTime.of(2027, 1, 11, 10, 0);
    private static final LocalDateTime ELEVEN = LocalDateTime.of(2027, 1, 11, 11, 0);

    private static TimeSlot slot(int startHour, int endHour) {
        return new TimeSlot(LocalDateTime.of(2027, 1, 11, startHour, 0),
                            LocalDateTime.of(2027, 1, 11, endHour, 0));
    }

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

    @Test
    void durationIsThirtyMinutes() {
        LocalDateTime tenThirty = LocalDateTime.of(2027, 1, 11, 10, 30);
        TimeSlot slot = new TimeSlot(TEN, tenThirty);
        assertEquals(Duration.ofMinutes(30), slot.duration());
    }

    @ParameterizedTest(name = "{0}-{1} vs {2}-{3} overlaps: {4}")
    @CsvSource({
        "10, 11, 11, 12, false",   // touching
        "10, 12, 11, 13, true",    // partly overlapping
        "8, 9, 10, 11, false",     // a before b
        "11, 12, 10, 13, true",    // a inside b
        "10, 11, 10, 11, true",    // identical
        "12, 13, 10, 11, false",   // a after b
        "10, 11, 10, 12, true",    // same start
        "10, 12, 11, 12, true",    // same end
    })
    void overlapCases(int aStart, int aEnd, int bStart, int bEnd, boolean expected) {
        assertEquals(expected, slot(aStart, aEnd).overlaps(slot(bStart, bEnd)));
        assertEquals(expected, slot(bStart, bEnd).overlaps(slot(aStart, aEnd)));  // A4
    }

    @Test
    void equalityUsesStartAndEnd() {
        assertEquals(slot(10, 11), slot(10, 11));
        assertEquals(slot(10, 11).hashCode(), slot(10, 11).hashCode());
        assertNotEquals(slot(10, 11), slot(9, 11));
        assertNotEquals(slot(10, 11), slot(10, 12));
        assertNotEquals(slot(10, 11), null);
    }
}
