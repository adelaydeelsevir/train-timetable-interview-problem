package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.joda.time.Minutes;

class JourneyPlanner {
    private final Timetable timetable;

    JourneyPlanner(String[][] timetableData) {
        this.timetable = TimetableFactory.createTimetableFrom(timetableData);
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
