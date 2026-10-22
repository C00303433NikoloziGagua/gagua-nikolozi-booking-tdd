import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public final class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (start == null) {
            throw new IllegalArgumentException("start must not be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("end must not be null");
        }
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("end must be after start");
        }
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Duration duration() {
        return Duration.between(start, end);
    }

    public boolean overlaps(TimeSlot other) {
        boolean startsBeforeOtherEnds = start.isBefore(other.end);
        boolean otherStartsBeforeThisEnds = other.start.isBefore(end);
        return startsBeforeOtherEnds && otherStartsBeforeThisEnds;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeSlot)) {
            return false;
        }
        TimeSlot other = (TimeSlot) obj;
        boolean sameStart = start.equals(other.start);
        boolean sameEnd = end.equals(other.end);
        return sameStart && sameEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
