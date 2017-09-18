package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

class Train {
    private final String[] trainTimetable;
    private final List<String> stations;

    Train(String[] trainTimetable, List<String> stations) {
        this.trainTimetable = trainTimetable;
        this.stations = stations;
    }

    LocalTime getTimeAt(String departureStation) {
        return localTimeFrom(trainTimetable[stations.indexOf(departureStation)]);
    }

    private static LocalTime localTimeFrom(String militaryTimeString) {
        return LocalTime.parse(militaryTimeString, DateTimeFormat.forPattern("HHmm"));
    }
}
