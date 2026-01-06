package leetcode.daily;

public class MagicSquaresInGrid {
    static boolean has3X3GridOneToNineDigits(int[][] grid, int rowOffset, int colOffset) {
        int[] digitFound = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = rowOffset; i < rowOffset + 3; i++) {
            for (int j = colOffset; j < colOffset + 3; j++) {
                if (grid[i][j] > 0 && grid[i][j] < 10) {
                    digitFound[grid[i][j] - 1] = 1;
                } else {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (digitFound[i] == 0) {
                return false;
            }
        }

        return true;
    }
    static int getRowWiseSum(int[][] grid, int rowOffset, int colOffset) {
        int sum = 0;

        for (int i = colOffset; i < colOffset + 3; i++) {
            sum += grid[rowOffset][i];
        }

        // System.out.println("row: " + rowOffset + ", col: " + colOffset + ". Sum: " + sum);
        return sum;
        
    }
    static int getColWiseSum(int[][] grid, int rowOffset, int colOffset) {
        int sum = 0;

        for (int i = rowOffset; i < rowOffset + 3; i++) {
            sum += grid[i][colOffset];
        }
        return sum;
        
    }
    static int getDiagonalWiseSum(int[][] grid, int rowOffset, int colOffset) {
        int sum = 0;

        for (int i = 0; i < 3; i++) {
            sum += grid[rowOffset + i][colOffset + i];
        }
        return sum;
        
    }
    static int getAntiDiagonalWiseSum(int[][] grid, int rowOffset, int colOffset) {
        int sum = 0;

        for (int i = 0; i < 3; i++) {
            sum += grid[rowOffset+i][colOffset + 2 - i];
        }
        return sum;
        
    }

    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, magicSquareCount = 0;

        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols - 2; j++) {
                if (
                    MagicSquaresInGrid.has3X3GridOneToNineDigits(grid, i, j) == true &&
                    MagicSquaresInGrid.getRowWiseSum(grid, i, j) == 15 &&
                    MagicSquaresInGrid.getRowWiseSum(grid, i + 1, j) == 15 &&
                    MagicSquaresInGrid.getRowWiseSum(grid, i + 2, j) == 15 &&
                    MagicSquaresInGrid.getColWiseSum(grid, i, j) == 15 &&
                    MagicSquaresInGrid.getColWiseSum(grid, i, j + 1) == 15 &&
                    MagicSquaresInGrid.getColWiseSum(grid, i, j + 2) == 15 &&
                    MagicSquaresInGrid.getDiagonalWiseSum(grid, i, j) == 15 &&
                    MagicSquaresInGrid.getAntiDiagonalWiseSum(grid, i, j) == 15
                ) {
                    magicSquareCount++;
                }
            }
        }

        return magicSquareCount;
    }
}