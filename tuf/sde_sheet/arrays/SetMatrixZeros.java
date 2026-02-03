package tuf.sde_sheet.arrays;

import java.util.HashSet;
import java.util.Set;

import utils.PerfUtil;
import utils.TestUtil;

public class SetMatrixZeros {
    private Set<Integer> rowsWithZero = new HashSet<>(), colsWithZeros = new HashSet<>();
    private int[] rowsWithZeroList;
    private int[] colsWithZeroList;

    private void findZeros(int[][] matrix) {
        this.rowsWithZeroList = new int[matrix.length];
        this.colsWithZeroList = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    if (!rowsWithZero.contains(i)) {
                        this.rowsWithZero.add(i);
                        this.rowsWithZeroList[this.rowsWithZero.size() - 1] = i;
                    }
                    if (!colsWithZeros.contains(j)) {
                        this.colsWithZeros.add(j);
                        this.colsWithZeroList[this.colsWithZeros.size() - 1] = j;
                    }
                }
            }
        }
    }
    private void populateZerosInFullMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(this.rowsWithZero.contains(i) || this.colsWithZeros.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    private void populateZerosInSelectedRowsCols(int[][] matrix) {
        for (int i = 0; i < this.rowsWithZero.size(); i++) {
            int rowNum = this.rowsWithZeroList[i];

            for (int j = 0; j < matrix[rowNum].length; j++) {
                matrix[rowNum][j] = 0;
            }
        }

        for (int i = 0; i < this.colsWithZeros.size(); i++) {
            int colNum = this.colsWithZeroList[i];

            for (int j = 0; j < matrix.length; j++) {
                matrix[j][colNum] = 0;
            }
        }
    }
    public void setZeroes(int[][] matrix) {
        this.findZeros(matrix);
        this.populateZerosInFullMatrix(matrix);
    }

    public void setZeroes(int[][] matrix, boolean traverseRowsColsWithZerosOnly) {
        this.findZeros(matrix);
        this.populateZerosInSelectedRowsCols(matrix);
    }

    public static void main(String[] args) {
        SetMatrixZeros solution = new SetMatrixZeros();

        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        solution.setZeroes(matrix);
        TestUtil.run(
            "Full Matrix Traversal: Test #1",
            new int[][]{{1,0,1},{0,0,0},{1,0,1}},
            matrix
        );

        solution = new SetMatrixZeros();
        matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        solution.setZeroes(matrix);
        TestUtil.run(
            "Full Matrix Traversal: Test #2",
            new int[][]{{0,0,0,0},{0,4,5,0},{0,3,1,0}},
            matrix
        );

        solution = new SetMatrixZeros();
        matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        solution.setZeroes(matrix, true);
        TestUtil.run(
            "Row, Col With Zero Only Traversal: Test #1",
            new int[][]{{1,0,1},{0,0,0},{1,0,1}},
            matrix
        );
        
        solution = new SetMatrixZeros();
        matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        solution.setZeroes(matrix, true);
        TestUtil.run(
            "Row, Col With Zero Only Traversal: Test #2",
            new int[][]{{0,0,0,0},{0,4,5,0},{0,3,1,0}},
            matrix
        );

        // Perf test to show O(m+n) memory can reduce time

        int n = 1000;
        final int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = 1;
            }
        }
        mat[500][500] = 0;

        System.out.println("Performace metrices for complete matrix traversal");
        final SetMatrixZeros sol = new SetMatrixZeros();
        PerfUtil.run(() -> {
            sol.setZeroes(mat);
        }, true);

        System.out.println("Performace metrices for rows and cols with zero only traversal");
        final SetMatrixZeros solu = new SetMatrixZeros();
        PerfUtil.run(() -> {
            solu.setZeroes(mat, true);
        }, true);
    }
}
