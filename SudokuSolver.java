public class SudokuSolver {

    public static boolean solveSudoku(int[][] board) {
        int N = board.length;

        // Find an empty cell (cell with value 0)
        int[] emptyCell = findEmptyCell(board);
        int row = emptyCell[0];
        int col = emptyCell[1];

        // If there are no empty cells, the puzzle is solved
        if (row == -1) {
            return true;
        }

        // Try filling the empty cell with values from 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                // Place the number if it's safe
                board[row][col] = num;

                // Recursively solve the rest of the puzzle
                if (solveSudoku(board)) {
                    return true;
                }

                // If no solution is found, backtrack
                board[row][col] = 0;
            }
        }

        // If no valid number can be placed, return false
        return false;
    }

    public static int[] findEmptyCell(int[][] board) {
        int N = board.length;
        int[] emptyCell = new int[]{-1, -1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    emptyCell[0] = i;
                    emptyCell[1] = j;
                    return emptyCell;
                }
            }
        }

        return emptyCell;
    }

    public static boolean isSafe(int[][] board, int row, int col, int num) {
        return !usedInRow(board, row, num) &&
                !usedInColumn(board, col, num) &&
                !usedInBox(board, row - row % 3, col - col % 3, num);
    }

    public static boolean usedInRow(int[][] board, int row, int num) {
        int N = board.length;
        for (int i = 0; i < N; i++) {
            if (board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean usedInColumn(int[][] board, int col, int num) {
        int N = board.length;
        for (int i = 0; i < N; i++) {
            if (board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean usedInBox(int[][] board, int boxStartRow, int boxStartCol, int num) {
        int N = board.length;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + boxStartRow][j + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printSudoku(int[][] board) {
        int N = board.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] sudokuBoard = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(sudokuBoard)) {
            System.out.println("Solved Sudoku:");
            printSudoku(sudokuBoard);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
