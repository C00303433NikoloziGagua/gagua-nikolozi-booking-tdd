interface BookingRule {
    void check(String userId, String roomId, TimeSlot slot);   // throws if the rule is broken
}
