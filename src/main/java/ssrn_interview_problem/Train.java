package ssrn_interview_problem;

import org.joda.time.LocalTime;

import java.util.Map;

class Train {
    private final Map<String, LocalTime> stationTimes;

    Train(Map<String, LocalTime> stationTimes) {
        this.stationTimes = stationTimes;
    }

    LocalTime getTimeAt(String departureStation) {
        return stationTimes.get(departureStation);
    }

}
