package pairmatching.model;

import java.util.Objects;

public class MissionInfo {

    private final Course course;
    private final Level level;
    private final Mission mission;

    public MissionInfo(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public boolean isBackend() {
        return course == Course.BACKEND;
    }

    public boolean isSameLevel(Level level) {
        return this.level == level;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MissionInfo that = (MissionInfo) o;
        return course == that.course && level == that.level && mission == that.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }
}
