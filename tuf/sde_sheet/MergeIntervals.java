package tuf.sde_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.TestUtil;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> mergedIntervals = new ArrayList<>();


        for (int i = 0; i < intervals.length;) {
            int start = intervals[i][0], end = intervals[i][1];

            while (i < intervals.length && intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
                i++;
            }

            mergedIntervals.add(new int[]{start, end});
        }
        // BELOW ADD 3MS. use toArray instead
        // int[][] result = new int[mergedIntervals.size()][2];

        // for (int i = 0; i < mergedIntervals.size(); i++) {
        //     result[i] = mergedIntervals.get(i);
        // }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        TestUtil.run("Test Case #1", new int[][]{{1,6},{8,10},{15,18}}, solution.merge(new int[][]{{2,6},{1,3},{8,10},{15,18}}));
        TestUtil.run("Test Case #2", new int[][]{{1,5}}, solution.merge(new int[][]{{1,4},{4,5}}));
        TestUtil.run("Test Case #3", new int[][]{{1,7}}, solution.merge(new int[][]{{4,7},{1,4}}));
    }
}
