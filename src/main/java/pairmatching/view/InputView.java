package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String READ_FUNCTION_NOTICE = String.join("\n"
            , "기능을 선택하세요."
            , "1. 페어 매칭"
            , "2. 페어 조회"
            , "3. 페어 초기화"
            , "Q. 종료"
    );

    public String readFunction() {
        System.out.println(READ_FUNCTION_NOTICE);
        return Console.readLine();
    }
}
