package atdd.path.docs;

import atdd.AbstractDocumentationTest;
import atdd.path.application.StationService;
import atdd.path.domain.Line;
import atdd.path.domain.Station;
import atdd.path.domain.StationTimetable;
import atdd.path.domain.Timetable;
import atdd.path.web.StationController;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static atdd.TestConstant.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StationController.class)
public class StationDocumentationTest extends AbstractDocumentationTest {
    @MockBean
    private StationService stationService;

    @Test
    void getTimetable() throws Exception {
        List<Line> persistLines = Lists.list(TEST_LINE, TEST_LINE_2);
        Station persistStation = new Station(STATION_ID, STATION_NAME, persistLines);

        List<String> upTimetable = Lists.list("00:00", "00:30", "23:00", "23:30");
        List<String> downTimetable = Lists.list("00:40", "01:10", "23:10", "23:40");

        Timetable timetable = new Timetable(upTimetable, downTimetable);

        StationTimetable stationTimetable = new StationTimetable(TEST_LINE.getId(), TEST_LINE.getName(), timetable);
        StationTimetable stationTimetable2 = new StationTimetable(TEST_LINE_2.getId(), TEST_LINE_2.getName(), timetable);

        given(stationService.retrieveTimetables(anyLong())).willReturn(Lists.list(stationTimetable, stationTimetable2));

        this.mockMvc.perform(get("/stations/" + persistStation.getId() + "/timetables")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("stations/timetable"))
                .andDo(print());
    }
}