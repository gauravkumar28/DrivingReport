import lombok.Builder;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;

@Builder
@ToString
public class Trip extends DrivingLogEntry {
    public String name;
    public String startTime;
    public String endTime;
    public double distance;

    boolean hasSpeedOutOfRange() {
        double speed = getSpeed();
        return (speed < 5 || speed > 100) ? true : false;
    }

    private double getSpeed() {
        Duration d = Duration.between(
                LocalTime.parse(startTime),
                LocalTime.parse(endTime)
        );
        long minutes = d.toMinutes();
        return (distance / minutes) * 60;
    }

    public Duration getDuration() {
        return Duration.between(
                LocalTime.parse(startTime),
                LocalTime.parse(endTime)
        );
    }
}
