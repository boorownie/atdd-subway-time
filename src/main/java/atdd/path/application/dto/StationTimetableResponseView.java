package atdd.path.application.dto;

import atdd.path.domain.StationTimetable;

import java.util.List;
import java.util.stream.Collectors;

public class StationTimetableResponseView {
    private Long lineId;
    private String lineName;
    private TimetableResponseView timetable;

    public StationTimetableResponseView(Long lineId, String lineName, TimetableResponseView timetable) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.timetable = timetable;
    }

    public static List<StationTimetableResponseView> listOf(List<StationTimetable> timetables) {
        return timetables.stream()
                .map(it -> StationTimetableResponseView.of(it))
                .collect(Collectors.toList());
    }

    private static StationTimetableResponseView of(StationTimetable stationTimetable) {
        return new StationTimetableResponseView(stationTimetable.getLineId(), stationTimetable.getLineName(), TimetableResponseView.of(stationTimetable.getTimetable()));
    }

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
