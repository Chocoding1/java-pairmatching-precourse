package pairmatching.config;

import pairmatching.controller.MatchingController;
import pairmatching.file.CrewReader;

public class AppConfig {

    private MatchingController matchingController;
    private CrewReader crewReader;

    public MatchingController matchingController() {
        if (matchingController == null) {
            matchingController = new MatchingController(crewReader());
        }
        return matchingController;
    }

    private CrewReader crewReader() {
        if (crewReader == null) {
            crewReader = new CrewReader();
        }
        return crewReader;
    }


}
