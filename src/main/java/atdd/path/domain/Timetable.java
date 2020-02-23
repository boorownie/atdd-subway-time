package atdd.path.domain;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Timetable {
    private List<String> up;
    private List<String> down;

    public Timetable(List<String> up, List<String> down) {
        this.up = up;
        this.down = down;
    }

    public static Timetable of(List<LocalTime> defaultTimetable, int upTime, int downTime) {
        List<String> up = calculateTimetable(defaultTimetable, upTime);
        List<String> down = calculateTimetable(defaultTimetable, downTime);
        return new Timetable(up, down);
    }

    private static List<String> calculateTimetable(List<LocalTime> defaultTimetable, int delayTime) {
        if (delayTime == -1) {
            return Arrays.asList();
        }
        return defaultTimetable.stream()
                .map(it -> it.plusMinutes(delayTime).toString())
                .collect(Collectors.toList());
    }


    public List<String> getUp() {
        return up;
    }

    public List<String> getDown() {
        return down;
    }
}
