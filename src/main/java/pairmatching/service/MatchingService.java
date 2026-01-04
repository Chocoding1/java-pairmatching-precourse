package pairmatching.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.model.BackendCrew;
import pairmatching.model.Crew;
import pairmatching.model.FrontendCrew;
import pairmatching.model.MissionInfo;
import pairmatching.model.Pair;
import pairmatching.model.RandomMatcher;

public class MatchingService {

    private final RandomMatcher randomMatcher;
    private int matchingCount;

    public MatchingService(RandomMatcher randomMatcher) {
        this.randomMatcher = randomMatcher;
        this.matchingCount = 0;
    }

    public List<Pair> match(MissionInfo missionInfo, BackendCrew backendCrew, FrontendCrew frontendCrew) {
        matchingCount++;

        List<Pair> pairs = new ArrayList<>();
        if (missionInfo.isBackend()) {
            List<String> crews = backendCrew.crewNames();
            List<String> shuffledCrew = randomMatcher.match(crews);
            setPairs(pairs, shuffledCrew);
            return pairs;
        }

        return pairs;
    }

    private void setPairs(List<Pair> pairs, List<String> shuffledCrew) {
        int crewCount = shuffledCrew.size();
        for (int i = 0; i < crewCount; i++) {
            if (i == crewCount - 1) {
                Pair lastPair = pairs.get(pairs.size() - 1);
                lastPair.addCrew(shuffledCrew.get(i));
            }
            pairs.add(new Pair(Arrays.asList(shuffledCrew.get(i), shuffledCrew.get(i + 1))));
            i++;
        }
    }
}
