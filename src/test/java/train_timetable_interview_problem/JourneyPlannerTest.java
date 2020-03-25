package train_timetable_interview_problem;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

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
    public void shouldReportDurationIncludingWaitingTimeOnPlatform() {
        // Given
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        // When
        int duration = journeyPlanner.durationOfJourneyStartingAt("1101", "St Austell", "Par");

        // Then
        assertThat(duration, is(equalTo(56)));
    }

    @Test
    public void shouldReportFastestTrainBetweenTwoStations() {
        // Given
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        // When I try to find the fastest train between "Exeter St Davids" and "London Paddington"
        String train = journeyPlanner.fastestTrainBetween("Exeter St Davids", "London Paddington");

        // Then I find the next train departs at "1357"
        assertThat(train, is(equalTo("1357")));
    }

    @Test
    public void shouldReportEarliestRouteWhenThereIsATieBetweenFastestTrains() {
        // Given
        JourneyPlanner journeyPlanner = new JourneyPlanner(TestData.TIMETABLE);

        // When
        String train = journeyPlanner.fastestTrainBetween("Par", "Bodmin Parkway");

        // Then
        assertThat(train, is(equalTo("1108")));
    }


}
