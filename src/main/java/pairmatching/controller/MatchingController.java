package pairmatching.controller;

import java.util.List;
import pairmatching.file.CrewReader;
import pairmatching.model.BackendCrew;
import pairmatching.model.FrontendCrew;

public class MatchingController {

    private final CrewReader crewReader;

    public MatchingController(CrewReader crewReader) {
        this.crewReader = crewReader;
    }

    public void run() {
        initCrew();
    }

    private void initCrew() {
        BackendCrew backendCrew = getBackendCrew();
        FrontendCrew frontendCrew = getFrontendCrew();
    }

    private BackendCrew getBackendCrew() {
        List<String> crewName = crewReader.getBackendCrew();
        return new BackendCrew(crewName);
    }

    private FrontendCrew getFrontendCrew() {
        List<String> crewName = crewReader.getFrontendCrew();
        return new FrontendCrew(crewName);
    }
}
