package pairmatching.model;

import java.util.List;

public class BackendGroup extends Group {

    public BackendGroup(List<String> crewNames) {
        super(crewNames, Course.BACKEND);
    }
}
