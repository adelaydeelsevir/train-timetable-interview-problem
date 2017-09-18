package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class JourneyPlanner {
    private final List<String> stations;
    private final List<Train> trains;

    JourneyPlanner(String[][] timetable) {
        stations = Arrays.asList(timetable[0]);
        trains = Arrays.stream(timetable)
                .skip(1)
                .map(trainTimes -> new Train(trainTimes, stations))
                .collect(Collectors.toList());
    }

    int getDurationBetween(LocalTime journeyStartTime, String departureStation, String arrivalStation) {
        Train firstAvailableTrainTimetable = trains.stream()
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
        Train fastestTrainTimetable = trains.stream()
                .sorted(Comparator.comparing(train -> Minutes.minutesBetween(train.getTimeAt(departureStation), train.getTimeAt(arrivalStation))))
                .findFirst()
                .get();

        return fastestTrainTimetable.getTimeAt(departureStation).toString("HHmm");
    }

}
