package ssrn_interview_problem;

import org.joda.time.LocalTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JourneyPlannerTest {
    @Test
    public void shouldReportDurationOfJourneyBetweenTwoStations() {
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);
        LocalTime journeyStartTime = new LocalTime(9, 7);

        int duration = journeyPlanner.getDurationBetween(journeyStartTime, "Camborne", "Exeter St Davids");

        assertThat(duration, is(equalTo(150)));
    }

    @Test
    public void shouldReportDurationForFirstAvailableTrain() {
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);
        LocalTime journeyStartTime = new LocalTime(10, 23);

        int duration = journeyPlanner.getDurationBetween(journeyStartTime, "Camborne", "Exeter St Davids");

        assertThat(duration, is(equalTo(159)));
    }
}
