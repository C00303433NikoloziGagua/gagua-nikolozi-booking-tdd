# NOTES

## Gaps

| Rule | What was unclear | Ruling | My test for it |
| ---- | ---------------- | ------ | -------------- |
| TimeSlot `toString` (Part A) | The brief lists `toString` as a required public member but never says what text it should return. | No ruling received. My assumption: it returns readable text that includes both the start and the end, in Java's record style, e.g. `TimeSlot[start=2027-01-11T10:00, end=2027-01-11T11:00]`. | `TimeSlotTest.toStringShowsStartAndEnd`: checks that the text contains both times rather than the exact format, so the test stays valid if a ruling picks a different layout. |

## AI corrections (at least three)

### 1
- What I asked:
- What it produced:
- What was wrong:
- The test that caught it:
