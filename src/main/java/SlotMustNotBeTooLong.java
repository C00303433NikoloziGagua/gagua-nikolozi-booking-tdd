import java.time.Duration;

class SlotMustNotBeTooLong implements BookingRule {
    private final Duration maxSlot;

    SlotMustNotBeTooLong(Duration maxSlot) {
        this.maxSlot = maxSlot;
    }

    public void check(String userId, String roomId, TimeSlot slot) {
        boolean tooLong = slot.duration().compareTo(maxSlot) > 0;
        if (tooLong) {
            throw new IllegalArgumentException("slot must last at most 3 hours");
        }
    }
}
