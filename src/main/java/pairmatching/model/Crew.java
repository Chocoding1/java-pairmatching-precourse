package pairmatching.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Crew {

    private final Course course;
    private final String name;
    private final List<PairInfo> pairLog;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
        this.pairLog = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean isNameEqual(String name) {
        return name.equals(this.name);
    }

    public boolean isAlreadyCrew(MissionInfo missionInfo, Crew pairCrew) {
        for (PairInfo pairInfo : pairLog) {
            if (pairInfo.contains(missionInfo, pairCrew)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAlreadyCrews(MissionInfo missionInfo, List<Crew> crews) {
        for (Crew crew : crews) {
            if (isAlreadyCrew(missionInfo, crew)) {
                return true;
            }
        }
        return false;
    }

    public void logCrew(MissionInfo missionInfo, Crew pairCrew) {
        pairLog.add(new PairInfo(pairCrew, missionInfo.getLevel()));
    }

    public void logCrews(MissionInfo missionInfo, List<Crew> crews) {
        for (Crew pairCrew : crews) {
            pairLog.add(new PairInfo(pairCrew, missionInfo.getLevel()));
            pairCrew.logCrew(missionInfo, this);
        }
    }

    public void deletePairInfo(Crew pairCrew, MissionInfo missionInfo) {
        for (PairInfo pairInfo : pairLog) {
            if (pairInfo.contains(missionInfo, pairCrew)) {
                pairLog.remove(pairInfo);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        return course == crew.course && Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, name);
    }
}
