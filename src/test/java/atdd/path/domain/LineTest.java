package atdd.path.domain;

import org.junit.jupiter.api.Test;

import static atdd.TestConstant.TEST_LINE;
import static atdd.TestConstant.TEST_STATION;
import static org.assertj.core.api.Assertions.assertThat;

public class LineTest {
    @Test
    void getTimetableOf() {
        StationTimetable stationTimetable = TEST_LINE.getTimetableOf(TEST_STATION);

        assertThat(stationTimetable.getLineId()).isEqualTo(TEST_LINE.getId());
        assertThat(stationTimetable.getLineName()).isEqualTo(TEST_LINE.getName());
        assertThat(stationTimetable.getTimetable().getUp()).isNotEmpty();
        assertThat(stationTimetable.getTimetable().getDown()).isNotEmpty();
    }
}
