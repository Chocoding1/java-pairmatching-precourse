package pairmatching.controller;

import static pairmatching.handler.ExceptionHandler.*;

import java.util.List;
import pairmatching.file.CrewReader;
import pairmatching.model.BackendCrew;
import pairmatching.model.FrontendCrew;
import pairmatching.model.FunctionNumber;
import pairmatching.model.MissionInfo;
import pairmatching.model.MissionParser;
import pairmatching.service.MatchingService;
import pairmatching.view.InputView;

public class MatchingController {

    private final CrewReader crewReader;
    private final InputView inputView;
    private final MissionParser missionParser;
    private final MatchingService matchingService;

    public MatchingController(CrewReader crewReader, InputView inputView, MissionParser missionParser, MatchingService matchingService) {
        this.crewReader = crewReader;
        this.inputView = inputView;
        this.missionParser = missionParser;
        this.matchingService = matchingService;
    }

    public void run() {
        BackendCrew backendCrew = getBackendCrew();
        FrontendCrew frontendCrew = getFrontendCrew();

        FunctionNumber functionNumber = retryUntilSuccess(this::readFunctionNumber);
        if (functionNumber.isNumber()) {
            if (functionNumber.isOne()) {
                MissionInfo missionInfo = retryUntilSuccess(this::readMissionDetail);

            }
        }
    }

    private BackendCrew getBackendCrew() {
        List<String> crewName = crewReader.getBackendCrew();
        return new BackendCrew(crewName);
    }

    private FrontendCrew getFrontendCrew() {
        List<String> crewName = crewReader.getFrontendCrew();
        return new FrontendCrew(crewName);
    }

    private FunctionNumber readFunctionNumber() {
        String inputFunction = inputView.readFunction();
        return new FunctionNumber(inputFunction);
    }

    private MissionInfo readMissionDetail() {
        String input = inputView.readMissionDetail();
        return missionParser.parse(input);
    }
}
