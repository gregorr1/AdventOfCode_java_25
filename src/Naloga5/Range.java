package Naloga5;

import java.util.Comparator;

public class Range {
    private long rangeStart;
    private long rangeEnd;

    public Range(long rangeStart, long rangeEnd) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public long getRangeStart() {
        return rangeStart;
    }
    public void setRangeStart(long rangeStart) {
        this.rangeStart = rangeStart;
    }
    public long getRangeEnd() {
        return rangeEnd;
    }
    public void setRangeEnd(long rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    // Add a comparator in order to sort Ranges
    public static final Comparator<Range> BY_START_THEN_END =
        Comparator.comparingLong(Range::getRangeStart)
                  .thenComparingLong(Range::getRangeEnd);

    @Override
    public String toString() {
        return rangeStart + " " + rangeEnd;
    }
}
