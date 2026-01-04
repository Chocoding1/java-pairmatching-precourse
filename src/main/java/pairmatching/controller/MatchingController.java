package pairmatching.controller;

import static pairmatching.handler.ExceptionHandler.*;

import java.util.List;
import java.util.function.Supplier;
import pairmatching.file.CrewReader;
import pairmatching.handler.ExceptionHandler;
import pairmatching.model.BackendCrew;
import pairmatching.model.FrontendCrew;
import pairmatching.model.FunctionNumber;
import pairmatching.service.MatchingService;
import pairmatching.view.InputView;

public class MatchingController {

    private final CrewReader crewReader;
    private final InputView inputView;
    private final MatchingService matchingService;

    public MatchingController(CrewReader crewReader, InputView inputView, MatchingService matchingService) {
        this.crewReader = crewReader;
        this.inputView = inputView;
        this.matchingService = matchingService;
    }

    public void run() {
        BackendCrew backendCrew = getBackendCrew();
        FrontendCrew frontendCrew = getFrontendCrew();

        FunctionNumber functionNumber = retryUntilSuccess(this::readFunctionNumber);
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
}
