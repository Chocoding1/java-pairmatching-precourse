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
    private static final String READ_MISSION_DETAIL_NOTICE = String.join("\n"
            , "#############################################"
            , "과정: 백엔드 | 프론트엔드"
            , "미션:"
            , "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임"
            , "  - 레벨2: 장바구니 | 결제 | 지하철노선도"
            , "  - 레벨3: "
            , "  - 레벨4: 성능개선 | 배포"
            , "  - 레벨5: "
            , "############################################"
            , "과정, 레벨, 미션을 선택하세요."
            , "ex) 백엔드, 레벨1, 자동차경주"
    );

    public String readFunction() {
        System.out.println(READ_FUNCTION_NOTICE);
        return Console.readLine();
    }

    public String readMissionDetail() {
        System.out.println(READ_MISSION_DETAIL_NOTICE);
        return Console.readLine();
    }
}
