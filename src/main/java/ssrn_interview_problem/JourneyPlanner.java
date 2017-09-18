package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class JourneyPlanner {
    private final Timetable timetable;

    JourneyPlanner(String[][] timetable) {
        List<String> stations = Arrays.asList(timetable[0]);
        List<Train> trains = Arrays.stream(timetable)
                .skip(1)
                .map(trainTimes -> new Train(trainTimes, stations))
                .collect(Collectors.toList());
        this.timetable = new Timetable(trains);
    }

    int getDurationBetween(LocalTime journeyStartTime, String departureStation, String arrivalStation) {
        Train firstAvailableTrain = timetable.getFirstAvailableTrainOnOrAfter(journeyStartTime, departureStation);

        LocalTime departureTime = firstAvailableTrain.getTimeAt(departureStation);
        LocalTime arrivalTime = firstAvailableTrain.getTimeAt(arrivalStation);

        Minutes timeWaitingForTrain = Minutes.minutesBetween(journeyStartTime, departureTime);
        Minutes timeOnTrain = Minutes.minutesBetween(departureTime, arrivalTime);

        return timeWaitingForTrain.plus(timeOnTrain).getMinutes();
    }

    String getFastestTrainBetween(String departureStation, String arrivalStation) {
        Train fastestTrain = timetable.getFastestTrainBetween(departureStation, arrivalStation);
        return fastestTrain.getTimeAt(departureStation).toString("HHmm");
    }

}
