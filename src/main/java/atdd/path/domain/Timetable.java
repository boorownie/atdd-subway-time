package atdd.path.domain;

import java.util.List;

public class Timetable {
    private List<String> up;
    private List<String> down;

    public Timetable(List<String> up, List<String> down) {
        this.up = up;
        this.down = down;
    }

    public List<String> getUp() {
        return up;
    }

    public List<String> getDown() {
        return down;
    }
}
