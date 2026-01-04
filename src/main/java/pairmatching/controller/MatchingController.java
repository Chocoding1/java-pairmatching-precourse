package pairmatching.controller;

import static pairmatching.handler.ExceptionHandler.*;

import java.util.Collections;
import java.util.List;
import pairmatching.exception.PairNotFoundException;
import pairmatching.file.CrewReader;
import pairmatching.model.BackendGroup;
import pairmatching.model.FrontendGroup;
import pairmatching.model.FunctionNumber;
import pairmatching.model.MatchingResult;
import pairmatching.model.MissionInfo;
import pairmatching.model.MissionParser;
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
        BackendGroup backendGroup = getBackendCrew();
        FrontendGroup frontendGroup = getFrontendCrew();

        while (true) {
            FunctionNumber functionNumber = retryUntilSuccess(this::readFunctionNumber);

            if (functionNumber.isQuit()) {
                break;
            }

            if (functionNumber.isOne()) {
                MatchingResult matchingResult = retryUntilSuccess(() -> matchingPair(backendGroup, frontendGroup));
                outputView.printMatchingResult(matchingResult);
                continue;
            }

            if (functionNumber.isTwo()) {
                MatchingResult matchingResult = retryUntilSuccess(this::findMatchingResult);
                if (matchingResult.isSuccess()) {
                    outputView.printMatchingResult(matchingResult);
                }
                continue;
            }

            if (functionNumber.isThree()) {
                matchingService.resetMatchingResults();
                outputView.printResetSuccess();
            }
        }

    }

    private BackendGroup getBackendCrew() {
        List<String> crewName = crewReader.getBackendCrew();
        return new BackendGroup(crewName);
    }

    private FrontendGroup getFrontendCrew() {
        List<String> crewName = crewReader.getFrontendCrew();
        return new FrontendGroup(crewName);
    }

    private FunctionNumber readFunctionNumber() {
        String inputFunction = inputView.readFunction();
        return new FunctionNumber(inputFunction);
    }

    private MissionInfo readMissionDetail() {
        String input = inputView.readMissionDetail();
        return missionParser.parse(input);
    }

    private MatchingResult matchingPair(BackendGroup backendGroup, FrontendGroup frontendGroup) {
        MatchingResult matchingResult;
        do {
            MissionInfo missionInfo = retryUntilSuccess(this::readMissionDetail);

            while (true) {
                matchingResult = matchingService.match(missionInfo, backendGroup, frontendGroup);

                if (matchingResult.isSuccess()) {
                    break;
                }

                String input = retryUntilSuccess(this::readRematch);
                if (input.equals("네")) {
                    matchingService.deleteMatchingResult(missionInfo);
                    continue;
                }
                break;
            }
        } while (!matchingResult.isSuccess());

        return matchingResult;
    }

    private MatchingResult findMatchingResult() {
        MissionInfo missionInfo = retryUntilSuccess(this::readMissionDetail);
        try {
            return matchingService.findMatchingResultByMissionInfo(missionInfo);
        } catch (PairNotFoundException e) {
            System.out.println(e.getMessage());
            return new MatchingResult(missionInfo, false, Collections.emptyList());
        }
    }

    private String readRematch() {
        String input = inputView.readRematch();
        validateRematchInput(input);
        return input;
    }

    private void validateRematchInput(String input) {
        if (!input.equals("네") && !input.equals("아니오")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력값입니다.");
        }
    }
}
