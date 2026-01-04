package pairmatching.controller;

import java.util.List;
import pairmatching.file.CrewReader;
import pairmatching.model.BackendCrew;
import pairmatching.model.FrontendCrew;
import pairmatching.view.InputView;

public class MatchingController {

    private final CrewReader crewReader;
    private final InputView inputView;

    public MatchingController(CrewReader crewReader, InputView inputView) {
        this.crewReader = crewReader;
        this.inputView = inputView;
    }

    public void run() {
        BackendCrew backendCrew = getBackendCrew();
        FrontendCrew frontendCrew = getFrontendCrew();

        String inputFunction = inputView.readFunction();
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
