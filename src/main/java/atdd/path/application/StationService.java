package atdd.path.application;

import atdd.path.dao.StationDao;
import atdd.path.domain.Station;
import atdd.path.domain.StationTimetable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private StationDao stationDao;

    public StationService(StationDao stationDao) {
        this.stationDao = stationDao;
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
        return null;
    }
}
