package atdd.path.domain;

public class StationTimetable {
    private Long lineId;
    private String lineName;
    private Timetable timetable;

    public StationTimetable() {
    }

    public StationTimetable(Long lineId, String lineName, Timetable timetable) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.timetable = timetable;
    }

    public Long getLineId() {
        return lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public Timetable getTimetable() {
        return timetable;
    }
}
