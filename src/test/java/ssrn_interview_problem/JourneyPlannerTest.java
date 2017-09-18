package ssrn_interview_problem;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JourneyPlannerTest {
    @Test
    public void shouldReportDurationOfJourneyBetweenTwoStations() {
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        int duration = journeyPlanner.getDurationBetween("Camborne", "Exeter St Davids");

        assertThat(duration, is(equalTo(150)));
    }
}
