package pairmatching.model;

import java.util.ArrayList;
import java.util.List;

public class MatchingResults {

    private List<MatchingResult> matchingResults;

    public MatchingResults() {
        this.matchingResults = new ArrayList<>();
    }

    public boolean isAlreadyExists(MissionInfo missionInfo) {
        for (MatchingResult matchingResult : matchingResults) {
            if (matchingResult.isSameInfo(missionInfo)) {
                return true;
            }
        }
        return false;
    }

    public void logResult(MatchingResult matchingResult) {
        matchingResults.add(matchingResult);
    }

    public void deleteResult(MissionInfo missionInfo) {
        for (MatchingResult matchingResult : matchingResults) {
            if (matchingResult.isSameInfo(missionInfo)) {
                matchingResult.deletePairLog(missionInfo);
                matchingResults.remove(matchingResult);
                break;
            }
        }
    }

    public MatchingResult findByMissionInfo(MissionInfo missionInfo) {
        for (MatchingResult matchingResult : matchingResults) {
            if (matchingResult.isSameInfo(missionInfo)) {
                return matchingResult;
            }
        }
        throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
    }

    public void reset() {
        for (MatchingResult matchingResult : matchingResults) {
            matchingResult.deleteAllPairLog();
            matchingResults = new ArrayList<>();
        }
    }
}
