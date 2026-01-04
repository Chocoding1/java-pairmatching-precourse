package pairmatching.config;

import pairmatching.controller.MatchingController;
import pairmatching.file.CrewReader;
import pairmatching.model.MissionParser;
import pairmatching.model.RandomMatcher;
import pairmatching.service.MatchingService;
import pairmatching.view.InputView;

public class AppConfig {

    private MatchingController matchingController;
    private CrewReader crewReader;
    private InputView inputView;
    private MissionParser missionParser;
    private MatchingService matchingService;
    private RandomMatcher randomMatcher;

    public MatchingController matchingController() {
        if (matchingController == null) {
            matchingController = new MatchingController(crewReader(), inputView(), missionParser(), matchingService());
        }
        return matchingController;
    }

    private CrewReader crewReader() {
        if (crewReader == null) {
            crewReader = new CrewReader();
        }
        return crewReader;
    }

    private MissionParser missionParser() {
        if (missionParser == null) {
            missionParser = new MissionParser();
        }
        return missionParser;
    }

    private InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    private MatchingService matchingService() {
        if (matchingService == null) {
            matchingService = new MatchingService(randomMatcher());
        }
        return matchingService;
    }

    private RandomMatcher randomMatcher() {
        if (randomMatcher == null) {
            randomMatcher = new RandomMatcher();
        }
        return randomMatcher;
    }
}
