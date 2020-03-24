package train_timetable_interview_problem;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JourneyPlanner {

    private String[][] timetable;

    public JourneyPlanner(String[][] timetable) {
        this.timetable = timetable;
    }

    public int durationOfJourneyStartingAt(String arriveAtStationTime, String startStationName, String endStationName) {

        String[] route = timetable[0];
        int startStationIndex = -1;

        for (int i = 0; i < route.length; i++) {
            if (route[i].equals(startStationName)) {
                startStationIndex = i;
                break;
            }
        }

        int endStationIndex = -1;

        for (int i = 0; i < route.length; i++) {
            if (route[i].equals(endStationName)) {
                endStationIndex = i;
                break;
            }
        }

        int train = -1;
        for (int i = 1; i < timetable.length; i++) {
            if (timetable[i][startStationIndex].equals(arriveAtStationTime)) {
                train = i;
                break;
            }
        }

        LocalTime startTime = LocalTime.parse(timetable[train][startStationIndex], DateTimeFormatter.ofPattern("HHmm"));
        LocalTime endTime = LocalTime.parse(timetable[train][endStationIndex], DateTimeFormatter.ofPattern("HHmm"));

        return (int) Duration.between(startTime, endTime).toMinutes();
    }
}
