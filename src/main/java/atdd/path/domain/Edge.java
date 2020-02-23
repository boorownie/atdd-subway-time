package atdd.path.domain;

public class Edge {
    private Long id;
    private Station sourceStation;
    private Station targetStation;
    private int distance;
    private int elapsedTime;

    public Edge() {
    }

    public Edge(Long id, Station sourceStation, Station targetStation, int distance, int elapsedTime) {
        this.id = id;
        this.sourceStation = sourceStation;
        this.targetStation = targetStation;
        this.distance = distance;
        this.elapsedTime = elapsedTime;
    }

    public static Edge of(Station sourceStation, Station targetStation, int distance, int elapsedTime) {
        return new Edge(null, sourceStation, targetStation, distance, elapsedTime);
    }

    public Long getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public Station getSourceStation() {
        return sourceStation;
    }

    public Station getTargetStation() {
        return targetStation;
    }

    public boolean hasStation(Station station) {
        return sourceStation.equals(station) || targetStation.equals(station);
    }
}
