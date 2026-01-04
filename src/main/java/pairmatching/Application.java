package pairmatching;

import pairmatching.config.AppConfig;
import pairmatching.controller.MatchingController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MatchingController matchingController = appConfig.matchingController();
        matchingController.run();
    }
}
