package leetcode.daily;

import utils.TestUtil;

public class SeparateSquaresI {
    private class Stats {
        public double yMIN, yMAX, totalArea;

        public Stats() {
            this.yMIN = 1e9;
            this.yMAX = 0;
            this.totalArea = 0;
        }
    }

    private Stats calculateMinMaxAndArea(int[][] squares) {
        Stats bSearchStats = new Stats();

        for (int[] square : squares) {
            double y = square[1], l = square[2];

            bSearchStats.yMIN = Math.min(y, bSearchStats.yMIN);
            bSearchStats.yMAX = Math.max(y + l, bSearchStats.yMAX);
            bSearchStats.totalArea += l * l;

        }

        return bSearchStats;
    }

    private double calculateAreaUnderY(int[][] squares, double y) {
        double area = 0.0d;

        for (int[] square : squares) {
            double yi = square[1];
            double l = square[2];

            if (yi >= y) {
                // square above y
                area += 0;
            } else if (yi + l <= y) {
                // square below y
                area += l * l;
            } else {
                // square on y
                area += (y - yi) * l;
            }
        }

        return area;
    }

    private double bSearch(int[][] squares, double start, double end, double area) {

        while (end - start > 1e-5) {
            double mid = (start + end) / 2.0d;
            double areaUnderMid = this.calculateAreaUnderY(squares, mid);

            if (areaUnderMid >= area) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return start;
    }

    public double separateSquares(int[][] squares) {
        Stats bSearchData = this.calculateMinMaxAndArea(squares);

        return this.bSearch(squares, bSearchData.yMIN, bSearchData.yMAX, (bSearchData.totalArea / 2.0d));
    }

    public static void main(String[] args) {
        SeparateSquaresI solution = new SeparateSquaresI();

        TestUtil.run(
                "Test Case #1",
                Math.abs(
                        1.00000 - solution.separateSquares(new int[][] { { 0, 0, 1 }, { 2, 2, 1 } })) < 1e5);

        TestUtil.run(
                "Test Case #2",
                Math.abs(
                        1.16667 - solution.separateSquares(new int[][] { { 0, 0, 2 }, { 1, 1, 1 } })) < 1e5);

        TestUtil.run(
                "Test Case #3",
                Math.abs(
                        954521423.80202 - solution.separateSquares(
                                new int[][] { { 522261215, 954313664, 225462 }, { 628661372, 718610752, 10667 },
                                        { 619734768, 941310679, 44788 }, { 352367502, 656774918, 289036 },
                                        { 860247066, 905800565, 100123 }, { 817623994, 962847576, 71460 },
                                        { 691552058, 782740602, 36271 }, { 911356, 152015365, 513881 },
                                        { 462847044, 859151855, 233567 }, { 672324240, 954509294, 685569 } })) < 1e5);

    }
}
