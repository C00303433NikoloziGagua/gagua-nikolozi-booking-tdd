import java.util.Objects;

public final class Booking {
    private final String userId;
    private final String roomId;
    private final TimeSlot slot;

    public Booking(String userId, String roomId, TimeSlot slot) {
        this.userId = userId;
        this.roomId = roomId;
        this.slot = slot;
    }

    public String getUserId() {
        return userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) obj;
        boolean sameUser = userId.equals(other.userId);
        boolean sameRoom = roomId.equals(other.roomId);
        boolean sameSlot = slot.equals(other.slot);
        return sameUser && sameRoom && sameSlot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roomId, slot);
    }
}
