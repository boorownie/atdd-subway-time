package atdd.path.docs;

import atdd.AbstractDocumentationTest;
import atdd.path.application.StationService;
import atdd.path.domain.Line;
import atdd.path.domain.Station;
import atdd.path.domain.StationTimetable;
import atdd.path.web.StationController;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.stream.Collectors;

import static atdd.TestConstant.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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

        List<StationTimetable> stationTimetables = persistLines.stream()
                .map(it -> it.getTimetableOf(persistStation))
                .collect(Collectors.toList());

        given(stationService.retrieveTimetables(anyLong())).willReturn(stationTimetables);

        FieldDescriptor[] timetable = new FieldDescriptor[]{
                fieldWithPath("lineId").type(JsonFieldType.NUMBER).description("지하철 노선 ID"),
                fieldWithPath("lineName").type(JsonFieldType.STRING).description("지하철 노선 이름"),
                fieldWithPath("timetable.up").type(JsonFieldType.ARRAY).description("지하철 노선의 상행선 시간표"),
                fieldWithPath("timetable.down").type(JsonFieldType.ARRAY).description("지하철 노선의 하행선 시간표")};

        this.mockMvc.perform(get("/stations/" + 1L + "/timetables")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("stations/timetable",
                        responseFields(
                                fieldWithPath("[]").description("지하철 노선 별 시간표 목록"))
                                .andWithPrefix("[].", timetable)))
                .andDo(print());
    }

}