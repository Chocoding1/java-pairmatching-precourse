package pairmatching.model;

public class FunctionNumber {

    private final String function;

    public FunctionNumber(String inputFunction) {
        validateFunction(inputFunction);
        this.function = inputFunction;
    }

    public boolean isNumber() {
        try {
            Integer.parseInt(function);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isOne() {
        return Integer.parseInt(function) == 1;
    }


    private void validateFunction(String inputFunction) {
        if (isInteger(inputFunction)) {
            int function = Integer.parseInt(inputFunction);
            if (function != 1 && function != 2 && function != 3) {
                throw new IllegalArgumentException("[ERROR] 존재하지 않는 기능입니다.");
            }
            return;
        }

        if (!inputFunction.equals("Q")) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 기능입니다.");
        }
    }

    private boolean isInteger(String inputFunction) {
        try {
            Integer.parseInt(inputFunction);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
