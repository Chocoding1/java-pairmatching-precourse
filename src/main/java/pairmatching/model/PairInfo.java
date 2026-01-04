package pairmatching.model;

import java.util.Objects;

public class PairInfo {

    private final Crew crew;
    private final Level level;

    public PairInfo(Crew crew, Level level) {
        this.crew = crew;
        this.level = level;
    }

    public boolean contains(MissionInfo missionInfo, Crew crew) {
        return missionInfo.isSameLevel(level) && this.crew == crew;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PairInfo pairInfo = (PairInfo) o;
        return Objects.equals(crew, pairInfo.crew) && level == pairInfo.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, level);
    }
}
