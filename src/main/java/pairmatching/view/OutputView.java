package pairmatching.view;

import java.util.List;
import pairmatching.model.MatchingResult;
import pairmatching.model.Pair;

public class OutputView {

    private static final String MATCHING_RESULT_TITLE = "페어 매칭 결과입니다.";
    private static final String RESET_SUCCESS_NOTICE = "초기화 되었습니다.";

    public void printMatchingResult(MatchingResult matchingResult) {
        List<Pair> pairs = matchingResult.getPairs();
        System.out.println(MATCHING_RESULT_TITLE);
        for (Pair pair : pairs) {
            System.out.println(String.join(" : ", pair.crewNames()));
        }
        System.out.println();
    }

    public void printResetSuccess() {
        System.out.println(RESET_SUCCESS_NOTICE);
    }
}
