package pairmatching.view;

import java.util.List;
import pairmatching.model.Pair;

public class OutputView {

    private static final String MATCHING_RESULT_TITLE = "페어 매칭 결과입니다.";

    public void printMatchingResult(List<Pair> pairs) {
        System.out.println(MATCHING_RESULT_TITLE);
        for (Pair pair : pairs) {
            System.out.println(String.join(" : ", pair.getCrews()));
        }
        System.out.println();
    }
}
