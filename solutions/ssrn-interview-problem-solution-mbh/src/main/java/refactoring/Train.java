package refactoring;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.lang.Math.toIntExact;

public class Train {

    private final String[] trainTimeTable;
    private final List<String> stationNames;

    Train(String[] trainTimeTable, List<String> stationNames) {
        this.trainTimeTable = trainTimeTable;
        this.stationNames = stationNames;
    }

    public LocalTime getTimeAt(String stationName) {
        return JourneyPlanner.parseToLocalTime(getTrainStartTimeAsString(stationName));
    }

    public boolean hasNotYetDeparted(String stationName, LocalTime currentTime) {
        LocalTime trainDepartureTime = this.getTimeAt(stationName);
        return trainDepartureTime.equals(currentTime) || trainDepartureTime.isAfter(currentTime);
    }

    public Integer journeyTimeBetween(String startStationName, String endStationName) {
        LocalTime trainDepartureTime = this.getTimeAt(startStationName);
        LocalTime trainArrivalTime = this.getTimeAt(endStationName);
        return toIntExact(ChronoUnit.MINUTES.between(trainDepartureTime, trainArrivalTime));
    }

    public String getTrainStartTimeAsString(String startStationName) {
        int stationIndex = this.stationNames.indexOf(startStationName);
        return this.trainTimeTable[stationIndex];
    }
}
