package leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;

import utils.TestUtil;

public class SeparateSquaresII {
    private class Rectangle {
        double y, l, b;

        public Rectangle(double y, double l, double b) {
            this.y = y;
            this.l = l;
            this.b = b;
        }
    }
    private class Stats {
        double min, max, totalArea;

        public Stats() {
            this.max = 0;
            this.min = 1e9;
            this.totalArea = 0;
        }
    }
    private ArrayList<Rectangle> mergeSqauresToRectangles(int[][] squares) {
        Arrays.sort(squares, (a, b)->{
            return a[1] == b[1] ? a[2] - b[2] : a[1] - b[1];
        });
        ArrayList<Rectangle> rectangles = new ArrayList<>();

        for (int i = 0; i < squares.length;) {
            int y = squares[i][1], l = squares[i][2], end = l + y;

            while (i < squares.length && squares[i][1] < end) {
                end = Math.max(end, squares[i][1]+squares[i][2]);
                i++;
            }

            rectangles.add(new Rectangle(y, end - y, l));
        }

        return rectangles;
    }
    private Stats calculateStats(ArrayList<Rectangle> rectangles) {
        Stats s = new Stats();

        for (Rectangle r: rectangles) {
            s.max = Math.max(s.max, r.y + r.l);
            s.min = Math.min(s.min, r.y);
            s.totalArea += r.l * r.b; 
        }

        return s;
    }
    private double calculateAreaUnderY(ArrayList<Rectangle> rectangles, double y) {
        double area = 0.0d;
    
        for (Rectangle rectangle: rectangles) {
            if (rectangle.y >= y) {
                return area;
            }

            area += rectangle.b * Math.min(rectangle.y + rectangle.l, y);
        }

        return area;
    }
    private double bSearch(ArrayList<Rectangle> rectangles, double start, double end, double area) {
        while (end - start > 1e-5) {
            double mid = (start + end) / 2.0;
            double areaUnderY = this.calculateAreaUnderY(rectangles, mid);

            if (areaUnderY >= area) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return start;
    }
    public double separateSquares(int[][] squares) {
        ArrayList<Rectangle> rectangles = this.mergeSqauresToRectangles(squares);

        Stats s = this.calculateStats(rectangles);

        return this.bSearch(rectangles, s.min, s.max, s.totalArea / 2);
    }

    public static void main(String[] args) {
        SeparateSquaresII solution = new SeparateSquaresII();

        // TestUtil.run(
        //     "Test Case #1",
        //     Math.abs( 1.00000 - solution.separateSquares(new int[][]{{0,0,1},{2,2,1}})) < 1e-5
        // );

        // TestUtil.run(
        //     "Test Case #2",
        //     Math.abs( 1.00000 - solution.separateSquares(new int[][]{{0,0,2},{1,1,1}})) < 1e-5
        // );

        TestUtil.run(
            "Test Case #3",
            Math.abs( 22.30000 - solution.separateSquares(new int[][]{{15,21,2},{19,21,3}})) < 1e-5
        );
    }
}
