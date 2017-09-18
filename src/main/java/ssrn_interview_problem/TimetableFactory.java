package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class TimetableFactory {
    static Timetable createTimetableFrom(String[][] timetableData) {
        List<String> stations = Arrays.asList(timetableData[0]);

        List<Train> trains = Arrays.stream(timetableData)
                .skip(1)
                .map(singleTrainTimetable -> new Train(getTimesAtStations(stations, singleTrainTimetable)))
                .collect(Collectors.toList());

        return new Timetable(trains);
    }

    private static Map<String, LocalTime> getTimesAtStations(List<String> stations, String[] trainTimes) {
        return stations.stream()
                .collect(Collectors.toMap(Function.identity(), station -> getTimeAt(station, stations, trainTimes)));
    }

    private static LocalTime getTimeAt(String station, List<String> stations, String[] trainTimes) {
        return LocalTime.parse(trainTimes[stations.indexOf(station)], DateTimeFormat.forPattern("HHmm"));
    }
}
