package atdd.path.application.dto;

import atdd.path.domain.Timetable;

import java.util.List;

public class TimetableResponseView {
    private List<String> up;
    private List<String> down;

    public TimetableResponseView() {
    }

    public TimetableResponseView(List<String> up, List<String> down) {
        this.up = up;
        this.down = down;
    }

    public static TimetableResponseView of(Timetable timetable) {
        return new TimetableResponseView(timetable.getUp(), timetable.getDown());
    }

    public List<String> getUp() {
        return up;
    }

    public List<String> getDown() {
        return down;
    }
}
