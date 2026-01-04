package pairmatching.config;

import pairmatching.controller.MatchingController;
import pairmatching.file.CrewReader;
import pairmatching.view.InputView;

public class AppConfig {

    private MatchingController matchingController;
    private CrewReader crewReader;
    private InputView inputView;

    public MatchingController matchingController() {
        if (matchingController == null) {
            matchingController = new MatchingController(crewReader(), inputView());
        }
        return matchingController;
    }

    private CrewReader crewReader() {
        if (crewReader == null) {
            crewReader = new CrewReader();
        }
        return crewReader;
    }

    private InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }


}
