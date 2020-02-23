package atdd.path.application.dto;

public class StationTimetableResponseView {
    private Long lineId;
    private String lineName;
    private TimetableResponseView timetable;

    public Long getLineId() {
        return lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public TimetableResponseView getTimetable() {
        return timetable;
    }
}
