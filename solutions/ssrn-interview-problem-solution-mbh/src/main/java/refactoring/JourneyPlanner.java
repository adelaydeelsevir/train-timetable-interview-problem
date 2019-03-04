package refactoring;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.toIntExact;

public class JourneyPlanner {
    private final RouteTimeTable routeTimeTable;

    JourneyPlanner(String[][] trainTimeTable) {
        this.routeTimeTable = new RouteTimeTable(trainTimeTable);
    }

    public int durationOfJourneyStartingAt(String arriveAtStationTimeString, String startStationName, String endStationName) {

        LocalTime arriveAtStationTime = parseToLocalTime(arriveAtStationTimeString);
        Train train = routeTimeTable.getNextAvailableTrain(startStationName, arriveAtStationTime);
        LocalTime timeAtEndStation = train.getTimeAt(endStationName);

        return toIntExact(ChronoUnit.MINUTES.between(arriveAtStationTime, timeAtEndStation));
    }

    public String fastestTrainBetween(String startStationName, String endStationName) {
        HashMap<Integer, List<String>> journeysByDuration
                = routeTimeTable.getJourneyTimesForRoute(startStationName, endStationName);

        return selectFastestJourney(journeysByDuration);
    }

    private String selectFastestJourney(HashMap<Integer, List<String>> journeysByDuration) {
        Integer minimumDuration = Collections.min(journeysByDuration.keySet());
        return journeysByDuration.get(minimumDuration).get(0);
    }

    public static LocalTime parseToLocalTime(String timeString) {
        return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
    }
}
