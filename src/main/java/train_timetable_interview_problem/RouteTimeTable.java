package train_timetable_interview_problem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RouteTimeTable {
    private final String[][] runningTimes;
    private final List<String> stationNames;
    private final List<Train> trains;

    RouteTimeTable(String[][] routeTimeTable) {
        this.runningTimes = Arrays.copyOfRange(routeTimeTable, 1, routeTimeTable.length);
        this.stationNames = Arrays.asList(routeTimeTable[0]);
        this.trains = getTrainsRunningOnRoute();
    }

    private List<Train> getTrainsRunningOnRoute() {
        List<Train> trains = new ArrayList<Train>();
        for (String[] trainTimes : this.runningTimes) {
            trains.add(new Train(trainTimes, this.stationNames));
        }
        return trains;
    }

    public Train getNextAvailableTrain(String startStationName, LocalTime currentTime) {

        for (Train train : this.trains) {
            if (train.hasNotYetDeparted(startStationName, currentTime)) {
                return train;
            }
        }
        return null;
    }

    public HashMap<Integer, List<String>> getJourneyTimesForRoute(String startStationName, String endStationName) {
        HashMap<Integer, List<String>> journeysByDuration = new HashMap<>();
        for (Train train : this.trains) {

            Integer duration = train.journeyTimeBetween(startStationName, endStationName);
            String startTimeString = train.getTrainStartTimeAsString(startStationName);
            journeysByDuration.computeIfAbsent(duration, integer -> new ArrayList<>()).add(startTimeString);

        }
        return journeysByDuration;
    }
}

