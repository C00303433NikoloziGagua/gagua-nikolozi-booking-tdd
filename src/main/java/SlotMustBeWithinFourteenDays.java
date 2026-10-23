import java.time.Clock;
import java.time.LocalDate;

class SlotMustBeWithinFourteenDays implements BookingRule {
    private final Clock clock;

    SlotMustBeWithinFourteenDays(Clock clock) {
        this.clock = clock;
    }

    public void check(String userId, String roomId, TimeSlot slot) {
        LocalDate lastAllowedDay = LocalDate.now(clock).plusDays(14);
        LocalDate startDay = slot.getStart().toLocalDate();
        if (startDay.isAfter(lastAllowedDay)) {
            throw new IllegalArgumentException("booking must start within 14 days");
        }
    }
}
