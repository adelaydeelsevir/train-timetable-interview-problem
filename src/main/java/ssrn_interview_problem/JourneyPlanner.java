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

        Train firstAvailableTrainTimetable = Arrays.stream(timetable)
                .skip(1)
                .map(trainTimes -> new Train(trainTimes, stations))
                .filter(train -> !train.getTimeAt(departureStation).isBefore(journeyStartTime))
                .findFirst()
                .get();

        LocalTime departureTime = firstAvailableTrainTimetable.getTimeAt(departureStation);
        LocalTime arrivalTime = firstAvailableTrainTimetable.getTimeAt(arrivalStation);

        Minutes timeWaitingForTrain = Minutes.minutesBetween(journeyStartTime, departureTime);
        Minutes timeOnTrain = Minutes.minutesBetween(departureTime, arrivalTime);

        return timeWaitingForTrain.plus(timeOnTrain).getMinutes();
    }

    String getFastestTrainBetween(String departureStation, String arrivalStation) {

        Train fastestTrainTimetable = Arrays.stream(timetable)
                .skip(1)
                .map(trainTimes -> new Train(trainTimes, stations))
                .sorted(Comparator.comparing(train -> Minutes.minutesBetween(train.getTimeAt(departureStation), train.getTimeAt(arrivalStation))))
                .findFirst()
                .get();

        return fastestTrainTimetable.getTimeAt(departureStation).toString("HHmm");
    }

}
