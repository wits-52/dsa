package utils;

public final class GridUtil {
    private GridUtil() {}

    // Generic for reference types
    public static <T> void printGrid(T[][] grid) {
        printGrid(grid, " ");
    }

    public static <T> void printGrid(T[][] grid, String delimiter) {
        if (grid == null) {
            System.out.println("null");
            return;
        }
        for (T[] row : grid) {
            if (row == null) {
                System.out.println("null");
                continue;
            }
            System.out.println(join(row, delimiter));
        }
    }

    // Overloads for common primitive grids
    public static void printGrid(int[][] grid) {
        printGrid(grid, " ");
    }

    public static void printGrid(int[][] grid, String delimiter) {
        if (grid == null) {
            System.out.println("null");
            return;
        }
        for (int[] row : grid) {
            if (row == null) {
                System.out.println("null");
                continue;
            }
            System.out.println(join(row, delimiter));
        }
    }

    public static void printGrid(long[][] grid) {
        printGrid(grid, " ");
    }

    public static void printGrid(long[][] grid, String delimiter) {
        if (grid == null) {
            System.out.println("null");
            return;
        }
        for (long[] row : grid) {
            if (row == null) {
                System.out.println("null");
                continue;
            }
            System.out.println(join(row, delimiter));
        }
    }

    public static void printGrid(double[][] grid) {
        printGrid(grid, " ");
    }

    public static void printGrid(double[][] grid, String delimiter) {
        if (grid == null) {
            System.out.println("null");
            return;
        }
        for (double[] row : grid) {
            if (row == null) {
                System.out.println("null");
                continue;
            }
            System.out.println(join(row, delimiter));
        }
    }

    public static void printGrid(char[][] grid) {
        printGrid(grid, " ");
    }

    public static void printGrid(char[][] grid, String delimiter) {
        if (grid == null) {
            System.out.println("null");
            return;
        }
        for (char[] row : grid) {
            if (row == null) {
                System.out.println("null");
                continue;
            }
            System.out.println(join(row, delimiter));
        }
    }

    public static void printGrid(boolean[][] grid) {
        printGrid(grid, " ");
    }

    public static void printGrid(boolean[][] grid, String delimiter) {
        if (grid == null) {
            System.out.println("null");
            return;
        }
        for (boolean[] row : grid) {
            if (row == null) {
                System.out.println("null");
                continue;
            }
            System.out.println(join(row, delimiter));
        }
    }

    // Helpers
    private static String join(Object[] arr, String delimiter) {
        if (arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(delimiter);
            sb.append(String.valueOf(arr[i]));
        }
        return sb.toString();
    }

    private static String join(int[] arr, String delimiter) {
        if (arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(delimiter);
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private static String join(long[] arr, String delimiter) {
        if (arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(delimiter);
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private static String join(double[] arr, String delimiter) {
        if (arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(delimiter);
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private static String join(char[] arr, String delimiter) {
        if (arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(delimiter);
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private static String join(boolean[] arr, String delimiter) {
        if (arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(delimiter);
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}

