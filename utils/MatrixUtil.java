package utils;

public final class MatrixUtil {
    public static int[][] multiply(int[][] matrixA, int[][] matrixB) throws Exception {
        if (matrixA.length == 0 || matrixA[0].length == 0 || matrixB.length == 0 || matrixB[0].length == 0) {
            throw new Exception("Matrix must have more then 0 rows and column for multiplication");
        }
        if (matrixA[0].length != matrixB.length) {
            throw new Exception("Matrix multiplication of given matices not possible.");
        }

        int[][] result = new int[matrixA.length][matrixB[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                int productSum = 0;
                for (int k = 0; k < matrixA[0].length; k++) {
                    productSum += matrixA[i][k] * matrixB[k][j];
                }
                result[i][j] = productSum;
            }
        }

        return result;
    }

    public static int[][] multiply(int[][] matrixA, int[][] matrixB, int mod) throws Exception {
        if (matrixA.length == 0 || matrixA[0].length == 0 || matrixB.length == 0 || matrixB[0].length == 0) {
            throw new Exception("Matrix must have more then 0 rows and column for multiplication");
        }
        if (matrixA[0].length != matrixB.length) {
            throw new Exception("Matrix multiplication of given matices not possible.");
        }

        int[][] result = new int[matrixA.length][matrixB[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                long productSum = 0L;
                for (int k = 0; k < matrixA[0].length; k++) {
                    productSum = (productSum + ((long)matrixA[i][k] * (long)matrixB[k][j]) % mod) % mod;
                }
                result[i][j] = (int) productSum;
            }
        }

        return result;
    }

    public static int[][] powerModM(int[][] matrix, int power, int mod) throws Exception{
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new Exception("Matrix must have more then 0 rows and column for multiplication");
        }
        if (power == 0) {
            int[][] result = new int[matrix[0].length][matrix[0].length];

            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    if (i == j) {
                        result[i][j] = 1;
                    } 
                }
            }
            return result;
        }

        if (power % 2 == 0) {
            return MatrixUtil.powerModM(MatrixUtil.multiply(matrix, matrix, mod), power / 2, mod);
        } else {
            return MatrixUtil.multiply(
                MatrixUtil.powerModM(MatrixUtil.multiply(matrix, matrix, mod), power / 2, mod),
                matrix,
                mod
            );
        }
    }
    public static boolean equals(int[][] a, int[][] b) {
        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length) {
                return false;
            }

            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
