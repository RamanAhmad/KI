import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class EightPuzzle {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int chooseHeuristic() {
        Scanner sc = new Scanner(System.in);
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

    public State searchGoalState(PriorityQueue<State> pq, int choice) {
        State goalState = null;
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int[][] board = cur.board;

            if (calculateCost(board, choice) == 0) {
                goalState = cur;
                break;
            }

            int[] pos = new int[2];
            findZero(board, pos);
            int x = pos[0];
            int y = pos[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                    int[][] newBoard = swapTiles(board, x, y, nx, ny);
                    State newState = new State(newBoard, nx, ny, calculateCost(newBoard, choice) + cur.depth + 1, cur.depth + 1, cur);
                    pq.add(newState);
                }
            }
        }
        return goalState;
    }

    public void printSolutionOrNoSolution(State goalState) {
        if (goalState != null) {
            printSolutionPath(goalState);
        } else {
            System.out.println("No solution found.");
        }
    }

    public void printSolutionPath(State goalState) {
        Stack<State> path = new Stack<>();
        State currentState = goalState;
        while (currentState != null) {
            path.push(currentState);
            currentState = currentState.parent;
        }

        while (!path.isEmpty()) {
            State state = path.pop();
            showResult(state.board);
        }
    }

    static int[][] swapTiles(int[][] board, int x, int y, int nx, int ny) {
        int[][] newBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, 3);
        }
        newBoard[x][y] = newBoard[nx][ny];
        newBoard[nx][ny] = 0;
        return newBoard;
    }

    static void showResult(int[][] arr) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void findZero(int[][] arr, int[] pos) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 0) {
                    pos[0] = i;
                    pos[1] = j;
                    return;
                }
            }
        }
    }

    public int calculateCost(int[][] board, int choice) {
        return switch (choice) {
            case 0 -> Heuristics.randomHeuristic(board);
            case 1 -> Heuristics.manhattanDistance(board);
            case 2 -> Heuristics.misplacedTiles(board);
            case 3 -> Heuristics.linearConflict(board) + Heuristics.randomHeuristic(board);
            case 4 -> Heuristics.linearConflict(board) + Heuristics.manhattanDistance(board);
            default -> throw new IllegalArgumentException("Invalid choice of heuristic function.");
        };
    }
}