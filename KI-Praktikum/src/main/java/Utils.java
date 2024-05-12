import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Utils {
    Scanner sc = new Scanner(System.in);


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

    public boolean isSolvable(int[][] board) {
        int[] arr = new int[9];
        int k = 0;
        for (int[] row : board) {
            for (int num : row) {
                arr[k++] = num;
            }
        }

        int inversions = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (arr[i] != 0 && arr[j] != 0 && arr[i] > arr[j]) {
                    inversions++;
                }
            }
        }

        return inversions % 2 == 0;
    }

    public int initialBoardOption() {
        System.out.println();

        System.out.println("Choose Initial Board");
        System.out.println("11. For solvable puzzle");
        System.out.println("22. For not solvable puzzle");
        System.out.println("-".repeat(41));
        String input = sc.nextLine();
        return Integer.parseInt(input);
    }


    public int heuristicOptions() {
        System.out.println();

        System.out.println("Choose heuristic function:");
        System.out.println("0. Our Heuristic ;)");
        System.out.println("1. Manhattan Distance");
        System.out.println("2. Misplaced Tiles");
        System.out.println("3. Linear Conflict and Manhattan Distance");
        System.out.println("4. Linear Conflict and Our Heuristic");
        System.out.println("-".repeat(41));
        System.out.println("5. Show Results");
        String input = sc.nextLine();
        return Integer.parseInt(input);
    }

    public int [][] initialBoard (int input){
       if (input==11){
           return new int[][] {
               {1, 8, 2},
               {0, 4, 3},
               {7, 6, 5}
           };
       }

        return new int[][] {
                {1, 5, 7},
                {8, 0, 6},
                {3, 2, 4}
        };
    }

}
