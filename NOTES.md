# NOTES

## Gaps

| Rule | What was unclear | Ruling | My test for it |
| ---- | ---------------- | ------ | -------------- |
| TimeSlot `toString` (Part A) | The brief lists `toString` as a required public member but never says what text it should return. | No ruling received. My assumption: it returns readable text that includes both the start and the end, in Java's record style, e.g. `TimeSlot[start=2027-01-11T10:00, end=2027-01-11T11:00]`. | `TimeSlotTest.toStringShowsStartAndEnd`: checks that the text contains both times rather than the exact format, so the test stays valid if a ruling picks a different layout. |

## AI corrections (at least three)

### 1
- What I asked: what "in the future" means in R2 for a slot that starts exactly now (09:00 on the test clock).
- What it produced: the assumption that a slot starting exactly now is allowed, i.e. reject only if `slot.getStart().isBefore(now)`.
- What was wrong: the lecturer's ruling on the Google Doc says "now is not in the future", so 09:00-10:00 must be rejected; `isBefore` lets it through.
- The test that caught it: no test caught it. It is not the kind of mistake a test can catch before the rule is known: the lecturer's ruling did. I then added the row `9, 10` to `BookingServiceTest.slotNotInFutureIsRejected`, so a test now guards against it.
