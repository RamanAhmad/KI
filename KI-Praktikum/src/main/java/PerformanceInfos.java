import java.util.List;

public class PerformanceInfos {
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
}
