import leetcode.LatestDayToCross;
import utils.TestUtil;
class Main {
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