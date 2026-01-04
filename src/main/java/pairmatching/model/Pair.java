package pairmatching.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {

    private final List<Crew> crews;

    public Pair(List<Crew> crews) {
        this.crews = new ArrayList<>(crews);
    }

    public void addCrew(Crew crew) {
        crews.add(crew);
    }

    public List<String> crewNames() {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(crews);
    }

    public void deletePairLog(MissionInfo missionInfo) {
        if (crews.size() == 2) {
            Crew firstCrew = crews.get(0);
            Crew secondCrew = crews.get(1);

            firstCrew.deletePairInfo(secondCrew, missionInfo);
            secondCrew.deletePairInfo(firstCrew, missionInfo);
            return;
        }

        if (crews.size() == 3) {
            Crew firstCrew = crews.get(0);
            Crew secondCrew = crews.get(1);
            Crew thirdCrew = crews.get(2);

            firstCrew.deletePairInfo(secondCrew, missionInfo);
            firstCrew.deletePairInfo(thirdCrew, missionInfo);

            secondCrew.deletePairInfo(firstCrew, missionInfo);
            secondCrew.deletePairInfo(thirdCrew, missionInfo);

            thirdCrew.deletePairInfo(firstCrew, missionInfo);
            thirdCrew.deletePairInfo(secondCrew, missionInfo);
        }
    }

    public void resetPairLog() {
        if (crews.size() == 2) {
            Crew firstCrew = crews.get(0);
            Crew secondCrew = crews.get(1);

            firstCrew.resetPairLog();
            secondCrew.resetPairLog();
            return;
        }

        if (crews.size() == 3) {
            Crew firstCrew = crews.get(0);
            Crew secondCrew = crews.get(1);
            Crew thirdCrew = crews.get(2);

            firstCrew.resetPairLog();
            secondCrew.resetPairLog();
            thirdCrew.resetPairLog();
        }
    }
}
