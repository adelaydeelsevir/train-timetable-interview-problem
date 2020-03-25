package train_timetable_interview_problem;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JourneyPlannerTest {

    private static final String[][] TIMETABLE = new String[][]
            {
                    {"Penzance", "St Erth", "Camborne", "Redruth", "Truro", "St Austell", "Par", "Bodmin Parkway", "Liskeard", "Plymouth", "Exeter St Davids", "Reading", "London Paddington"},
                    {"0844", "0854", "0907", "0914", "0927", "0944", "0951", "1003", "1016", "1040", "1137", "1316", "1344"},
                    {"1000", "1010", "1023", "1030", "1043", "1100", "1108", "1119", "1133", "1157", "1302", "1450", "1521"},
                    {"1047", "1057", "1112", "1119", "1132", "1150", "1157", "1208", "1221", "1252", "1357", "1539", "1602"}
            };

    @Test
    public void shouldReportDurationOfJourneyBetweenTwoStations() {
        JourneyPlanner journeyPlanner = new JourneyPlanner(TIMETABLE);

        int duration = journeyPlanner.durationOfJourneyStartingAt("0907", "Camborne", "Exeter St Davids");

        assertThat(duration, is(equalTo(150)));
    }

    @Test
    public void shouldReportDurationForFirstAvailableTrain() {
        JourneyPlanner journeyPlanner = new JourneyPlanner(TIMETABLE);

        int duration = journeyPlanner.durationOfJourneyStartingAt("1023", "Camborne", "Exeter St Davids");

        assertThat(duration, is(equalTo(159)));
    }

    @Test
    public void shouldReportDurationIncludingWaitingTimeOnPlatform() {
        JourneyPlanner journeyPlanner = new JourneyPlanner(TIMETABLE);

        int duration = journeyPlanner.durationOfJourneyStartingAt("1101", "St Austell", "Par");

        assertThat(duration, is(equalTo(56)));
    }

    @Test
    public void shouldReportFastestTrainBetweenTwoStations() {
        JourneyPlanner journeyPlanner = new JourneyPlanner(TIMETABLE);

        String fastestTrain = journeyPlanner.fastestTrainBetween("Exeter St Davids", "London Paddington");

        assertThat(fastestTrain, is(equalTo("1357")));
    }

    @Test
    public void shouldReportEarliestRouteWhenThereIsATieBetweenFastestTrains() {
        JourneyPlanner journeyPlanner = new JourneyPlanner(TIMETABLE);

        String fastestTrain = journeyPlanner.fastestTrainBetween("Par", "Bodmin Parkway");

        assertThat(fastestTrain, is(equalTo("1108")));
    }

}
