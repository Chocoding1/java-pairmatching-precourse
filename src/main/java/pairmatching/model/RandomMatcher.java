package pairmatching.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomMatcher {

    public List<String> match(List<String> crew) {
        return Randoms.shuffle(crew);
    }
}
