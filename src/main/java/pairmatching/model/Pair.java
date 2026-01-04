package pairmatching.model;

import java.util.List;

public class Pair {

    private final List<String> crews;

    public Pair(List<String> crews) {
        this.crews = crews;
    }

    public void addCrew(String name) {
        crews.add(name);
    }

    public List<String> getCrews() {
        return crews;
    }
}
