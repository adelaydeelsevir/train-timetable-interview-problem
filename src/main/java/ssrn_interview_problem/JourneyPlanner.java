package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class JourneyPlanner {
    private final String[][] timetable;
    private final List<String> stations;

    JourneyPlanner(String[][] timetable) {
        this.timetable = timetable;
        stations = Arrays.asList(timetable[0]);
    }

    int getDurationBetween(LocalTime journeyStartTime, String departureStation, String arrivalStation) {
        int departureStationIndex = stations.indexOf(departureStation);
        int arrivalStationIndex = stations.indexOf(arrivalStation);

        String[] firstAvailableTrainTimetable = Arrays.stream(timetable)
                .skip(1)
                .filter(trainTimetable -> !localTimeFrom(trainTimetable[departureStationIndex]).isBefore(journeyStartTime))
                .findFirst()
                .get();

        LocalTime departureTime = localTimeFrom(firstAvailableTrainTimetable[departureStationIndex]);
        LocalTime arrivalTime = localTimeFrom(firstAvailableTrainTimetable[arrivalStationIndex]);

        Minutes timeWaitingForTrain = Minutes.minutesBetween(journeyStartTime, departureTime);
        Minutes timeOnTrain = Minutes.minutesBetween(departureTime, arrivalTime);

        return timeWaitingForTrain.plus(timeOnTrain).getMinutes();
    }

    String getFastestTrainBetween(String departureStation, String arrivalStation) {
        int departureStationIndex = stations.indexOf(departureStation);
        int arrivalStationIndex = stations.indexOf(arrivalStation);

        String[] fastestTrainTimetable = Arrays.stream(timetable)
                .skip(1)
                .sorted(Comparator.comparing(trainTimetable -> Minutes.minutesBetween(localTimeFrom(trainTimetable[departureStationIndex]), localTimeFrom(trainTimetable[arrivalStationIndex]))))
                .findFirst()
                .get();

        return fastestTrainTimetable[departureStationIndex];
    }

    private static LocalTime localTimeFrom(String militaryTimeString) {
        return LocalTime.parse(militaryTimeString, DateTimeFormat.forPattern("HHmm"));
    }

}
