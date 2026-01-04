package pairmatching.model;

import java.util.List;

public class FrontendGroup extends Group{

    public FrontendGroup(List<String> crewNames) {
        super(crewNames, Course.FRONTEND);
    }
}
