package pairmatching.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrewReader {

    private static final String BACKEND_CREW_FILE = "C:\\sj\\wooteco\\final-cote-practice\\java-pairmatching-precourse\\src\\main\\resources\\backend-crew.md";
    private static final String FRONTEND_CREW_FILE = "C:\\sj\\wooteco\\final-cote-practice\\java-pairmatching-precourse\\src\\main\\resources\\frontend-crew.md";

    public List<String> getBackendCrew() {
        return getCrew(BACKEND_CREW_FILE);
    }

    public List<String> getFrontendCrew() {
        return getCrew(FRONTEND_CREW_FILE);
    }

    private List<String> getCrew(String crewFile) {
        List<String> crewName = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(crewFile))) {
            while (sc.hasNextLine()) {
                crewName.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 파일입니다.");
        }
        return crewName;
    }
}
