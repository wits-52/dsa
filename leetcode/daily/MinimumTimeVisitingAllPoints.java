package leetcode.daily;

import utils.TestUtil;

public class MinimumTimeVisitingAllPoints {
    public int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;

        for (int i = 0; i < points.length - 1; i++) {
            int xDistance = Math.abs(points[i+1][0] - points[i][0]), yDistance = Math.abs(points[i+1][1] - points[i][1]);

            time += Math.min(xDistance, yDistance);
            time += Math.max(xDistance, yDistance) - Math.min(xDistance, yDistance);
        }

        return time;
    }
    public static void main(String[] args) {
        MinimumTimeVisitingAllPoints solution = new MinimumTimeVisitingAllPoints();

        TestUtil.run("Test Case #1", 7, solution.minTimeToVisitAllPoints(new int[][]{{1,1},{3,4},{-1,0}}));
        TestUtil.run("Test Case #2", 5, solution.minTimeToVisitAllPoints(new int[][]{{3,2},{-2,2}}));
    }
}
