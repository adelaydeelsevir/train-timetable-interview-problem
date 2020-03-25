package train_timetable_interview_problem;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JourneyPlanner {

    private String[][] timetable;

    public JourneyPlanner(String[][] timetable) {
        this.timetable = timetable;
    }

    public int durationOfJourneyStartingAt(String arriveAtStationTime, String startStationName, String endStationName) {

        String[] route = timetable[0];

        int startStationIndex = getStationIndex(startStationName, route);

        int train = -1;
        for (int i = 1; i < timetable.length; i++) {
            if (Integer.parseInt(timetable[i][startStationIndex]) >= Integer.parseInt(arriveAtStationTime)) {
                train = i;
                break;
            }
        }

        int endStationIndex = getStationIndex(endStationName, route);

        return getDurationBetweenTwoStations(arriveAtStationTime, timetable[train][endStationIndex]);
    }

    private int getDurationBetweenTwoStations(String startStationTime, String endStationTime) {
        LocalTime startTime = LocalTime.parse(startStationTime, DateTimeFormatter.ofPattern("HHmm"));
        LocalTime endTime = LocalTime.parse(endStationTime, DateTimeFormatter.ofPattern("HHmm"));
        return (int) Duration.between(startTime, endTime).toMinutes();
    }

    private int getStationIndex(String startStationName, String[] route) {
        int startStationIndex;
        startStationIndex = -1;
        for (int i = 0; i < route.length; i++) {
            if (route[i].equals(startStationName)) {
                startStationIndex = i;
                break;
            }
        }
        return startStationIndex;
    }

    public String fastestTrainBetween(String startStationName, String endStationName) {
        int startStationIndex = getStationIndex(startStationName, timetable[0]);
        int endStationIndex = getStationIndex(endStationName, timetable[0]);

        List<Train> trains = new ArrayList<>();

        for (int train = 1; train < timetable.length; train++) {
            trains.add(new Train(train, getDurationBetweenTwoStations(timetable[train][startStationIndex], timetable[train][endStationIndex])));
        }

        Comparator<Train> compareByJourneyTime = Comparator.comparing(Train::getJourneyTime);
        Collections.sort(trains, compareByJourneyTime);

        return timetable[trains.get(0).getTrain()][startStationIndex];
    }

    private class Train {
        private final int train;
        private final Integer journeyTime;

        public Train(int train, Integer journeyTime) {
            this.train = train;
            this.journeyTime = journeyTime;
        }

        public Integer getJourneyTime() {
            return this.journeyTime;
        }

        public int getTrain() {
            return train;
        }
    }
}
