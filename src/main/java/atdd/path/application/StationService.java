package atdd.path.application;

import atdd.path.dao.LineDao;
import atdd.path.dao.StationDao;
import atdd.path.domain.Line;
import atdd.path.domain.Station;
import atdd.path.domain.StationTimetable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService {
    private StationDao stationDao;
    private LineDao lineDao;

    public StationService(StationDao stationDao, LineDao lineDao) {
        this.stationDao = stationDao;
        this.lineDao = lineDao;
    }

    public void deleteById(Long id) {
        stationDao.deleteById(id);
    }

    public List<Station> show() {
        return stationDao.findAll();
    }

    public Station retrieveStation(Long id) {
        return stationDao.findById(id);
    }

    public Station save(Station station) {
        return stationDao.save(station);
    }

    public List<StationTimetable> retrieveTimetables(Long stationId) {
        Station persistStation = stationDao.findById(stationId);
        List<Long> lineIds = persistStation.getLines().stream()
                .map(it -> it.getId())
                .collect(Collectors.toList());
        List<Line> lines = lineDao.findByIds(lineIds);
        return lines.stream()
                .map(it -> it.getTimetableOf(persistStation))
                .collect(Collectors.toList());
    }
}
