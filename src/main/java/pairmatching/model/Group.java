package pairmatching.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Group {

    private final List<Crew> crews;

    public Group(List<String> crewNames, Course course) {
        ArrayList<Crew> crews = new ArrayList<>();
        for (String name : crewNames) {
            crews.add(new Crew(course, name));
        }
        this.crews = Collections.unmodifiableList(crews);
    }

    public List<String> crewNames() {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

    public Crew findByName(String name) {
        for (Crew crew : crews) {
            if (crew.isNameEqual(name)) {
                return crew;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 크루입니다");
    }
}
