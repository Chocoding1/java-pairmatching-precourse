package pairmatching.model;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    ;

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static Course from(String name) {
        for (Course course : values()) {
            if (name.equals(course.name)) {
                return course;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 코스입니다.");
    }
}
