package pairmatching.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.model.BackendGroup;
import pairmatching.model.Crew;
import pairmatching.model.FrontendGroup;
import pairmatching.model.Group;
import pairmatching.model.MatchingResult;
import pairmatching.model.MatchingResults;
import pairmatching.model.MissionInfo;
import pairmatching.model.Pair;
import pairmatching.model.RandomMatcher;

public class MatchingService {

    private final RandomMatcher randomMatcher;
    private final MatchingResults matchingResults;

    public MatchingService(RandomMatcher randomMatcher, MatchingResults matchingResults) {
        this.randomMatcher = randomMatcher;
        this.matchingResults = matchingResults;
    }

    public MatchingResult match(MissionInfo missionInfo, BackendGroup backendGroup, FrontendGroup frontendGroup) {
        if (matchingResults.isAlreadyExists(missionInfo)) {
            return new MatchingResult(missionInfo, false, Collections.emptyList());
        }

        int matchingCount = 0;
        while (true) {
            checkMatchingCount(matchingCount);
            matchingCount++;

            List<Pair> pairs = new ArrayList<>();
            if (missionInfo.isBackend()) {
                if (matchPairs(missionInfo, backendGroup, pairs)) {
                    MatchingResult matchingResult = new MatchingResult(missionInfo, true, pairs);
                    matchingResults.logResult(matchingResult);
                    return matchingResult;
                }
                continue;
            }

            if (matchPairs(missionInfo, frontendGroup, pairs)) {
                MatchingResult matchingResult = new MatchingResult(missionInfo, true, pairs);
                matchingResults.logResult(matchingResult);
                return matchingResult;
            }
        }
    }

    public void deleteMatchingResult(MissionInfo missionInfo) {
        matchingResults.deleteResult(missionInfo);
    }

    private void checkMatchingCount(int matchingCount) {
        if (matchingCount == 3) {
            throw new IllegalArgumentException("[ERROR] 매칭할 수 있는 경우의 수가 존재하지 않습니다.");
        }
    }

    private boolean matchPairs(MissionInfo missionInfo, Group group, List<Pair> pairs) {
        List<String> shuffledNames = randomMatcher.match(group.crewNames());
        List<Crew> shuffledCrews = shuffledNames.stream()
                .map(group::findByName)
                .collect(Collectors.toList());
        return setPairs(missionInfo, pairs, shuffledCrews);
    }

    private boolean setPairs(MissionInfo missionInfo, List<Pair> pairs, List<Crew> shuffledCrews) {
        int crewCount = shuffledCrews.size();
        for (int i = 0; i < crewCount; i++) {
            if (i == crewCount - 1) {
                Pair lastPair = pairs.get(pairs.size() - 1);
                Crew lastCrew = shuffledCrews.get(i);

                if (lastCrew.isAlreadyCrews(missionInfo, lastPair.getCrews())) {
                    return false;
                }

                lastCrew.logCrews(missionInfo, lastPair.getCrews());
                lastPair.addCrew(lastCrew);
                continue;
            }

            Crew firstCrew = shuffledCrews.get(i);
            Crew secondCrew = shuffledCrews.get(i + 1);

            if (isAlreadyCrew(missionInfo, firstCrew, secondCrew)) {
                return false;
            }

            pairs.add(new Pair(Arrays.asList(firstCrew, secondCrew)));
            firstCrew.logCrew(missionInfo, secondCrew);
            secondCrew.logCrew(missionInfo, firstCrew);
            i++;
        }
        return true;
    }

    private boolean isAlreadyCrew(MissionInfo missionInfo, Crew firstCrew, Crew secondCrew) {
        return firstCrew.isAlreadyCrew(missionInfo, secondCrew);
    }

    public MatchingResult findMatchingResultByMissionInfo(MissionInfo missionInfo) {
        return matchingResults.findByMissionInfo(missionInfo);
    }
}
