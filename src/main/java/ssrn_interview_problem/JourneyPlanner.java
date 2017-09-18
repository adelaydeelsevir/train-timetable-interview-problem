package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;

import java.util.Arrays;
import java.util.List;

class JourneyPlanner {
    private final String[][] timetable;
    private final List<String> stations;

    JourneyPlanner(String[][] timetable) {
        this.timetable = timetable;
        stations = Arrays.asList(timetable[0]);
    }

    int getDurationBetween(String departureStation, String arrivalStation) {
        int departureStationIndex = stations.indexOf(departureStation);
        int arrivalStationIndex = stations.indexOf(arrivalStation);
        String[] firstTrainTimetable = timetable[1];
        LocalTime departureTime = LocalTime.parse(firstTrainTimetable[departureStationIndex], DateTimeFormat.forPattern("HHmm"));
        LocalTime arrivalTime = LocalTime.parse(firstTrainTimetable[arrivalStationIndex], DateTimeFormat.forPattern("HHmm"));
        return Minutes.minutesBetween(departureTime, arrivalTime).getMinutes();
    }
}
