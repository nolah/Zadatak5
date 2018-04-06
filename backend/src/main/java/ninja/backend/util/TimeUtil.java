package ninja.backend.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

import ninja.backend.api.dto.TimeDto;


public final class TimeUtil {

    private TimeUtil() {
    }

    public static TimeDto calculateTimeDifference(ZonedDateTime start, ZonedDateTime end) {

        final long startSeconds = start.toEpochSecond();
        final long endSeconds = end.toEpochSecond();
        //Require.badRequestUnless(endSeconds >= startSeconds, "Time difference cannot be negative!");
        long totalDurationDelta = endSeconds - startSeconds;
        long totalDuration = totalDurationDelta;

        final long days = TimeUnit.DAYS.convert(totalDurationDelta, TimeUnit.SECONDS);
        totalDurationDelta = totalDurationDelta - TimeUnit.SECONDS.convert(days, TimeUnit.DAYS);

        final long hours = TimeUnit.HOURS.convert(totalDurationDelta, TimeUnit.SECONDS);
        totalDurationDelta = totalDurationDelta - TimeUnit.SECONDS.convert(hours, TimeUnit.HOURS);

        final long minutes = TimeUnit.MINUTES.convert(totalDurationDelta, TimeUnit.SECONDS);

        return new TimeDto(Math.toIntExact(days), Math.toIntExact(hours), Math.toIntExact(minutes), totalDuration * 1000);
    }

    public static long calculateMillisecondsDifference(ZonedDateTime start, ZonedDateTime end) {
        final long startMillis = start.toInstant().toEpochMilli();
        final long endMillis = end.toInstant().toEpochMilli();
        //Require.badRequestUnless(endMillis >= startMillis, "Time difference cannot be negative!");
        return endMillis - startMillis;
    }

    public static TimeDto convertMillisToTimeDifference(long millis) {
        long millisDelta = millis;

        final long days = TimeUnit.DAYS.convert(millisDelta, TimeUnit.MILLISECONDS);
        millisDelta = millisDelta - TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS);

        final long hours = TimeUnit.HOURS.convert(millisDelta, TimeUnit.MILLISECONDS);
        millisDelta = millisDelta - TimeUnit.MILLISECONDS.convert(hours, TimeUnit.HOURS);

        final long minutes = TimeUnit.MINUTES.convert(millisDelta, TimeUnit.MILLISECONDS) < 1 ? 1 : TimeUnit.MINUTES.convert(millisDelta, TimeUnit.MILLISECONDS);

        return new TimeDto(Math.toIntExact(days), Math.toIntExact(hours), Math.toIntExact(minutes), millis);
    }

    public static ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

}
