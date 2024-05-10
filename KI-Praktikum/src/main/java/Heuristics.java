/**
 * The methode {@link Heuristics} includes all heuristics
 */


public class Heuristics {

        // Method to calculate Manhattan distance heuristic
        static int manhattanDistance(int[][] board) {
            int result = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) continue;
                    int y = (board[i][j] - 1) % 3;
                    int x = (board[i][j] - 1) / 3;
                    result += Math.abs(x - i) + Math.abs(y - j);
                }
            }
            return result;
        }

        // Method to calculate Tiles out of place heuristic
        static int misplacedTiles(int[][] board) {
            int result = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 0 && board[i][j] != i * 3 + j + 1) {
                        result++;
                    }
                }
            }
            return result;
        }

    static int randomHeuristic(int[][] board) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 0) {
                    result++;
                }
                if(board[i][j] == i * 3 + j + 1){
                    result--;
                }
            }
        }
        return result;
    }

        // Method to calculate Linear Conflict heuristic
        static int linearConflict(int[][] board) {
            int conflict = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int value = board[i][j];
                    if (value != 0 && (value - 1) / 3 == i) {
                        for (int k = j + 1; k < 3; k++) {
                            int nextValue = board[i][k];
                            if (nextValue != 0 && (nextValue - 1) / 3 == i && value > nextValue) {
                                conflict++;
                            }
                        }
                    }
                }
            }
            return conflict;
        }
    }

