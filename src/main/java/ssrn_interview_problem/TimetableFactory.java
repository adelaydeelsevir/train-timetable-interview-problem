package ssrn_interview_problem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class TimetableFactory {
    static Timetable createTimetableFrom(String[][] timetableData) {
        List<String> stations = Arrays.asList(timetableData[0]);

        List<Train> trains = Arrays.stream(timetableData)
                .skip(1)
                .map(trainTimes -> new Train(trainTimes, stations))
                .collect(Collectors.toList());

        return new Timetable(trains);
    }
}
