package pairmatching.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MissionParser {

    private static final String COMMA = ",";
    private static final int COURSE_INDEX = 0;
    private static final int LEVEL_INDEX = 1;
    private static final int MISSION_INDEX = 2;

    public MissionInfo parse(String input) {
        List<String> tokens = splitByComma(input);
        validateTokenCount(tokens);
        return createMissionInfo(tokens);
    }

    private List<String> splitByComma(String input) {
        return Arrays.stream(input.split(COMMA))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void validateTokenCount(List<String> tokens) {
        if (tokens.size() != 3) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 형식입니다.");
        }
    }

    private MissionInfo createMissionInfo(List<String> tokens) {
        Course course = Course.from(tokens.get(COURSE_INDEX));
        Level level = Level.from(tokens.get(LEVEL_INDEX));
        Mission mission = Mission.from(tokens.get(MISSION_INDEX));

        return new MissionInfo(course, level, mission);
    }
}
