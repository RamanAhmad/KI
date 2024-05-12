import java.util.*;

public class Solver {
    public static void main(String[] args) {
        EightPuzzle eightPuzzle = new EightPuzzle();
        Utils utils = new Utils();
        List<Utils.ChoiceInfo> choiceInfoList = new ArrayList<>();
        int[][] initialBoard;
        int inputInitialBoard = utils.initialBoardOption();

        if (inputInitialBoard == 22) {
            initialBoard = utils.initialBoard(22);
            System.out.println("No solutions available - inversion count problem!");
        } else if (inputInitialBoard == 11) {
            initialBoard = utils.initialBoard(11);

            while (true) {
                int choice = utils.heuristicOptions();

                if (choice == 5) {
                    Utils.displayChoiceInfo(choiceInfoList);
                    break;
                }

                var heuristicName = switch (choice) {
                    case 0 -> "Our Heuristic";
                    case 1 -> "Manhattan Distance";
                    case 2 -> "Misplaced Tiles";
                    case 3 -> "Linear Conflict and OH";
                    default -> "LC and MD";
                };

                PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
                int[] pos = new int[2];
                eightPuzzle.findZero(initialBoard, pos);
                State initialState = new State(initialBoard, pos[0], pos[1], eightPuzzle.calculateCost(initialBoard, choice), 0, null);
                pq.add(initialState);

                long startTime = System.nanoTime();
                long memoryBefore = 0;
                long memoryAfter = 0;

                System.gc();
                memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                State goalState = eightPuzzle.searchGoalState(pq, choice);

                System.gc();
                memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                long endTime = System.nanoTime();
                long memoryUsed = memoryAfter - memoryBefore;
                long duration = (endTime - startTime) / 1000000;
                utils.printSolutionOrNoSolution(goalState);
                choiceInfoList.add(new Utils.ChoiceInfo(choice, heuristicName, duration, memoryUsed));
            }
        }
    }
}
