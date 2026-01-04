package pairmatching.model;

import java.util.ArrayList;
import java.util.List;

public class Crew {

    private final Course course;
    private final String name;
    private List<String> pairLog;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
        this.pairLog = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
}
