package leetcode.daily;

import java.util.HashSet;
import java.util.Set;

import utils.TestUtil;

public class LatestDayToCross {
    private int[][] island;
    private boolean findPathToRightdfs(int i, int j, Set<String> visited) {
        String setKey = String.format("%d:%d", i, j);
        if(visited.contains(setKey)) {
            return false;
        }
        visited.add(setKey);

        if (i < 0 || j < 0 || j >= this.island[0].length || i >= this.island.length) {
            return false;
        }
        if (this.island[i][j] == 0) {
            return false;
        }
        if (j == this.island[i].length - 1) {
            return true;
        }

        return findPathToRightdfs(i, j-1, visited) || findPathToRightdfs(i-1, j, visited) ||
        findPathToRightdfs(i, j+1, visited) || findPathToRightdfs(i+1, j, visited) ;
    }
    private boolean hasConnectedHorizontalPath() {
        boolean pathFound = false;
        for (int i = 0; i < island.length; i++) {
            Set<String> visited = new HashSet<>();
            if (this.island[i][0] == 1) {
                pathFound = this.findPathToRightdfs(i, 0, visited);

                if(pathFound) return pathFound;
            }
        }

        return pathFound;
    }
    private void markWaterIfIsolated(int i, int j) {
        if (i < 0 || j < 0 || i >= this.island.length || j>= this.island[0].length || this.island[i][j] == 1) {
            // escape invalid cells
            return;
        }
        boolean leftWater = false, rightWater = false, downWater = false;
        if (j == 0 || this.island[i][j-1] == 1) {
            leftWater = true;
        }
        if (j == this.island[i].length - 1 || this.island[i][j+1] == 1) {
            rightWater = true;
        }
        if (i != this.island.length - 1 && this.island[i+1][j] == 1) {
            downWater = true;
        }
        if (i == this.island.length - 1 && this.island[i-1][j] == 1 && leftWater && rightWater) {
            downWater = true;
        }

        if (leftWater && rightWater && downWater) {
            this.island[i][j] = 1;
        }
    }
    public int latestDayToCross(int row, int col, int[][] cells) {
        this.island = new int[row][col];

        for (int i = 0; i < cells.length; i++) {
            int r = cells[i][0]-1, c = cells[i][1]-1;
            this.island[r][c] = 1;

            this.markWaterIfIsolated(r-1, c);
            this.markWaterIfIsolated(r, c-1);
            this.markWaterIfIsolated(r, c+1);
            this.markWaterIfIsolated(r+1, c);

            if (this.hasConnectedHorizontalPath()) {
                return i;
            }
        }

        return cells.length;
    }
    public static void main(String[] args) {
        LatestDayToCross solution = new LatestDayToCross();

        TestUtil.run("Test Case 1", 2, solution.latestDayToCross(
            2, 
            2, 
            new int[][]{{1,1},{2,1},{1,2},{2,2}}
        ));

        TestUtil.run("Test Case 2", 1, solution.latestDayToCross(
            2, 
            2, 
            new int[][]{{1,1},{1,2},{2,1},{2,2}}
        ));

        TestUtil.run("Test Case 3", 3, solution.latestDayToCross(
            3, 
            3, 
            new int[][]{{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}}
        ));


        TestUtil.run("Test Case 4", 7, solution.latestDayToCross(
            2, 
            6, 
            new int[][]{{2,5},{1,6},{1,1},{2,2},{2,3},{1,5},{2,1},{1,4},{2,6},{2,4},{1,2},{1,3}}
        ));

        TestUtil.run("Test Case 5", 2, solution.latestDayToCross(
            4, 
            3, 
            new int[][]{{3,2},{3,1},{2,3},{1,2},{2,1},{4,1},{1,3},{4,3},{3,3},{2,2},{1,1},{4,2}}
        ));
    }
}