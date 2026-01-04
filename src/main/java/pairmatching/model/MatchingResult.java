package pairmatching.model;

import java.util.Collections;
import java.util.List;

public class MatchingResult {

    private final MissionInfo missionInfo;
    private final boolean matchResult;
    private final List<Pair> pairs;

    public MatchingResult(MissionInfo missionInfo, boolean matchResult, List<Pair> pairs) {
        this.missionInfo = missionInfo;
        this.matchResult = matchResult;
        this.pairs = Collections.unmodifiableList(pairs);
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public boolean isSameInfo(MissionInfo missionInfo) {
        return this.missionInfo.equals(missionInfo);
    }

    public boolean isSuccess() {
        return matchResult;
    }

    public void deletePairLog(MissionInfo missionInfo) {
        for (Pair pair : pairs) {
            pair.deletePairLog(missionInfo);
        }
    }

    public void deleteAllPairLog() {
        for (Pair pair : pairs) {
            pair.resetPairLog();
        }
    }
}
