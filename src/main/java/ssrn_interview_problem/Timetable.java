package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.Comparator;
import java.util.List;

class Timetable {
    private final List<Train> trains;

    Timetable(List<Train> trains) {
        this.trains = trains;
    }

    Train getFastestTrainBetween(String departureStation, String arrivalStation) {
        return trains.stream()
                .sorted(Comparator.comparing(train -> Minutes.minutesBetween(train.getTimeAt(departureStation), train.getTimeAt(arrivalStation))))
                .findFirst()
                .get();
    }

    Train getFirstAvailableTrainOnOrAfter(LocalTime journeyStartTime, String departureStation) {
        return trains.stream()
                .filter(train -> !train.getTimeAt(departureStation).isBefore(journeyStartTime))
                .findFirst()
                .get();
    }
}
