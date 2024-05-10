import java.util.*;

public class Solver {
    public static void main(String[] args) {
        EightPuzzle eightPuzzle = new EightPuzzle();
        List<PerformanceInfos.ChoiceInfo> choiceInfoList = new ArrayList<>();

        while (true) {
            int[][] initialBoard = {
                    {0, 1, 2},
                    {5, 4, 6},
                    {7, 8, 3}
            };

            int choice = eightPuzzle.chooseHeuristic();

            if (choice == 4) {
                PerformanceInfos.displayChoiceInfo(choiceInfoList);
                break;
            }

            var heuristicName = switch (choice) {
                case 0 -> "Our Heuristic";
                case 1 -> "Manhattan Distance";
                case 2 -> "Misplaced Tiles";
                default -> "LC and MD";
            };

            PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
            int[] pos = new int[2];
            EightPuzzle.findZero(initialBoard, pos);
            State initialState = new State(initialBoard, pos[0], pos[1], eightPuzzle.calculateCost(initialBoard, choice), 0, null);
            pq.add(initialState);

            long startTime = System.nanoTime();
            long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            State goalState = eightPuzzle.searchGoalState(pq, choice);

            long endTime = System.nanoTime();
            long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long duration = (endTime - startTime) / 1000000; // Umwandlung von Nanosekunden in Millisekunden
            long memoryUsed = memoryAfter - memoryBefore;

            eightPuzzle.printSolutionOrNoSolution(goalState);
            choiceInfoList.add(new PerformanceInfos.ChoiceInfo(choice, heuristicName, duration, memoryUsed));
        }
    }

}
