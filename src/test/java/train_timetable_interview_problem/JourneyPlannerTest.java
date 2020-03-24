package train_timetable_interview_problem;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class JourneyPlannerTest {

    @Test
    public void shouldReportDurationOfJourneyBetweenTwoStations() {
        // Given
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        // When
        int duration = journeyPlanner.durationOfJourneyStartingAt("0907", "Camborne", "Exeter St Davids");

        // Then
        assertThat(duration, is(equalTo(150)));
    }

    @Test
    public void shouldReportDurationForFirstAvailableTrain() {
        // Given
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        // When
        int duration = journeyPlanner.durationOfJourneyStartingAt("1023", "Camborne", "Exeter St Davids");

        // Then
        assertThat(duration, is(equalTo(159)));
    }

    @Test
    @Ignore
    public void shouldReportDurationIncludingWaitingTimeOnPlatform() {
        // Given
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        // When
        int duration = journeyPlanner.durationOfJourneyStartingAt("1101", "St Austell", "Par");

        // Then
        assertThat(duration, is(equalTo(56)));
    }

    @Test
    @Ignore
    public void shouldReportFastestTrainBetweenTwoStations() {
        // Given
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        // When I try to find the fastest train between "Exeter St Davids" and "London Paddington"

        // Then I find the next train departs at "1357"
    }

    @Test
    @Ignore
    public void shouldReportEarliestRouteWhenThereIsATieBetweenFastestTrains() {
        // Given
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        // When

        // Then
    }


}
