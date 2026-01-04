package pairmatching.controller;

import static pairmatching.handler.ExceptionHandler.*;

import java.util.List;
import pairmatching.file.CrewReader;
import pairmatching.model.BackendCrew;
import pairmatching.model.FrontendCrew;
import pairmatching.model.FunctionNumber;
import pairmatching.model.MissionInfo;
import pairmatching.model.MissionParser;
import pairmatching.model.Pair;
import pairmatching.service.MatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {

    private final CrewReader crewReader;
    private final InputView inputView;
    private final MissionParser missionParser;
    private final MatchingService matchingService;
    private final OutputView outputView;

    public MatchingController(CrewReader crewReader, InputView inputView, MissionParser missionParser,
                              MatchingService matchingService, OutputView outputView) {
        this.crewReader = crewReader;
        this.inputView = inputView;
        this.missionParser = missionParser;
        this.matchingService = matchingService;
        this.outputView = outputView;
    }

    public void run() {
        BackendCrew backendCrew = getBackendCrew();
        FrontendCrew frontendCrew = getFrontendCrew();

        while (true) {
            FunctionNumber functionNumber = retryUntilSuccess(this::readFunctionNumber);

            if (functionNumber.isQuit()) {
                break;
            }

            if (functionNumber.isOne()) {
                MissionInfo missionInfo = retryUntilSuccess(this::readMissionDetail);
                List<Pair> pairs = matchingService.match(missionInfo, backendCrew, frontendCrew);
                outputView.printMatchingResult(pairs);
                continue;
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
