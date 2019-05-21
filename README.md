# SSRN Interview Problem

## Context
You have been asked to build an application to help people plan their train journeys on the Great Western Railway line.

Your job is to implement a journey planner that provides travel times and the fastest trains based on a supplied timetable.  You may use Google or another search engine to find reference resources for Java.
 
## Acceptance Criteria
|Criterion|Given|When|Then|
|---|-----|----|----|
|*Report Duration Of Journey Between Two Stations*|The 0907 train from Camborne is scheduled to reach Exeter St Davids at 1137|I ask for the journey time from Camborne to Exeter St Davids if I am at the station at 0907|I am told it will take 150 minutes|
|*Report Duration For First Available Train*|The 0907, 1023 and 1112 trains from Camborne are scheduled to reach Exeter St Davids at 1137, 1302 and 1357, respectively|I ask for the journey time from Camborne to Exeter St Davids if I am at the station at 1023|I am told it will take 159 minutes|
|*Report Duration Including Waiting Time On Platform*|The 0944, 1100 and 1150 trains from St Austell are scheduled to reach Par at 0951, 1108 and 1157, respectively|I ask for the journey time from St Austell to Par if I am at the station at 1101|I am told it will take 56 minutes|
|*Report Fastest Train Between Two Stations*|The 1137, 1302 and 1357 trains from Exeter St Davids are scheduled to reach Paddington at 1344, 1521 and 1602, respectively|I ask for the fastest train from Exeter St Davids to Paddington|I am told the 1357 is fastest|
|*Report Earliest Route When There Is A Tie Between Fastest Trains*|The 0951, 1108 and 1157 trains from Par are scheduled to reach Bodmin Parkway at 1003, 1119 and 1208, respectively|I ask for the fastest train from Par to Bodmin Parkway|I am told the 1108 is fastest|

## Technical Notes
* The full train timetable for testing is provided in the `ssrn_interview_problem.TestData` class.
* The journey planner should be written in Java 1.8.
* The application should be test-driven.
* It should be possible to run the tests using `gradle clean test`