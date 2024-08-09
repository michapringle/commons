package ca.mpringle.util;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public final class Timer {

    private final long startTimeInMillis;
    @NotNull
    private final List<Long> elapsedTimesInMillis;

    private Timer(final long startTimeInMillis) {

        this.startTimeInMillis = Checks.notNullAnd(startTimeInMillis).isGreaterThan(0L, "start time must be > 0");
        this.elapsedTimesInMillis = new ArrayList<>();
    }

    @NotNull
    public static Timer createStarted() {

        return new Timer(Instant.now().toEpochMilli());
    }

    public long split() {

        final long split = Instant.now().toEpochMilli() - startTimeInMillis;
        elapsedTimesInMillis.add(split);
        return split;
    }

    public long getSplit(final int i) {

        return elapsedTimesInMillis.get(i);
    }

    @NotNull
    public List<Long> getElapsedTimesInMillis() {

        return new ArrayList<>(elapsedTimesInMillis);
    }

    @Override
    @NotNull
    public String toString() {

        final StringBuilder builder = new StringBuilder("start: +0\n");

        for (final long elapsedTimesInMilli : elapsedTimesInMillis) {
            builder
                    .append("split: +")
                    .append(elapsedTimesInMilli)
                    .append("\n");
        }

        return builder.toString();
    }
}
