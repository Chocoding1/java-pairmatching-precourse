package pairmatching.model;

public enum Mission {
    CAR_RACE("자동차 경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    NUMBER_BASEBALL("숫자야구게임", Level.LEVEL1),

    SHOPPING_BASKET("장바구니", Level.LEVEL2),
    PAYMENT("결제", Level.LEVEL2),
    SUBWAY_MAP("지하철노선도", Level.LEVEL2),

    IMPROVE_PERFORMANCE("성능개선", Level.LEVEL4),
    RELEASE("배포", Level.LEVEL4),
    ;

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission from(String name) {
        for (Mission mission : values()) {
            if (name.equals(mission.name)) {
                return mission;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
    }
}
