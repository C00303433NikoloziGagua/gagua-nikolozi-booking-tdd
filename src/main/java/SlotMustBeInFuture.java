import java.time.Clock;
import java.time.LocalDateTime;

class SlotMustBeInFuture implements BookingRule {
    private final Clock clock;

    SlotMustBeInFuture(Clock clock) {
        this.clock = clock;
    }

    public void check(String userId, String roomId, TimeSlot slot) {
        LocalDateTime now = LocalDateTime.now(clock);
        if (!slot.getStart().isAfter(now)) {
            throw new IllegalArgumentException("slot must be in the future");
        }
    }
}
