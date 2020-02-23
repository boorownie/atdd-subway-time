package atdd.path.application;

import atdd.path.dao.LineDao;
import atdd.path.dao.StationDao;
import atdd.path.domain.Line;
import atdd.path.domain.Station;
import atdd.path.domain.StationTimetable;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static atdd.TestConstant.*;
import static atdd.TestConstant.STATION_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = StationService.class)
public class StationServiceTest {
    @Autowired
    private StationService stationService;
    @MockBean
    private StationDao stationDao;
    @MockBean
    private LineDao lineDao;

    @Test
    void retrieveTimetables() {
        List<Line> persistLines = Lists.list(TEST_LINE, TEST_LINE_2);
        Station persistStation = new Station(STATION_ID, STATION_NAME, persistLines);
        given(stationDao.findById(anyLong())).willReturn(persistStation);
        given(lineDao.findByIds(anyList())).willReturn(persistLines);

        List<StationTimetable> stationTimetables = stationService.retrieveTimetables(persistStation.getId());

        assertThat(stationTimetables.size()).isEqualTo(2);
    }
}
