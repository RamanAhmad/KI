import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {
    public static void displayChoiceInfo(List<ChoiceInfo> choiceInfoList) {
        System.out.printf("%-10s\t%-20s\t%-20s\t%-20s\n", "Choice", "Heuristic", "Execution Time (ms)", "Memory Used (Bytes)");
        for (ChoiceInfo choiceInfo : choiceInfoList) {
            System.out.printf("%-10d\t%-20s\t%-20d\t%-20d\n",
                    choiceInfo.choice(), choiceInfo.heuristicName(),
                    choiceInfo.duration(), choiceInfo.memoryUsed());
        }
    }

    // Innere Klasse zum Speichern von Informationen zu jeder Auswahl
        public record ChoiceInfo(int choice, String heuristicName, long duration, long memoryUsed) {
    }

    public int[][] generateRandomBoard() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        int[][] randomBoard = new int[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                randomBoard[i][j] = numbers.get(k++);
            }
        }
        return randomBoard;
    }

}
