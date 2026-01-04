package pairmatching.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BackendCrew {

    private final List<Crew> crews;

    public BackendCrew(List<String> crewNames) {
        ArrayList<Crew> crews = new ArrayList<>();
        for (String name : crewNames) {
            crews.add(new Crew(Course.BACKEND, name));
        }
        this.crews = Collections.unmodifiableList(crews);
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public List<String> crewNames() {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}
