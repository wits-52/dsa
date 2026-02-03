package tuf.sde_sheet.arrays_IV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import utils.TestUtil;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<String> usedQuads = new HashSet<>();

        Arrays.sort(nums);

        int a = 0, d = nums.length - 1;

        while (a+1 < d-1 && a < nums.length - 3 && d > 2) {
            int b = a+1, c = d-1;
            long newTarget = (long)target - (long)nums[a] - (long)nums[d];
            while (b < c) {
                if ((long)nums[b] + (long)nums[c] == newTarget) {
                    String quadrupleHash = nums[a] + ":" + nums[b] + ":" + nums[c] + ":" + nums[d];

                    if (!usedQuads.contains(quadrupleHash)) {
                        usedQuads.add(quadrupleHash);
                        result.add(Arrays.asList(new Integer[]{nums[a], nums[b], nums[c], nums[d]}));
                    }
                    b++;c--;
                } else if (nums[b] + nums[c] < newTarget) {
                    b++;
                } else {
                    c--;
                }
            }
            if ((long)nums[d-2]+(long)nums[d-1] == newTarget) {
                a++;
                if (d < nums.length - 1) {
                    d++;
                }
            } else if ((long)nums[d-2]+(long)nums[d-1] > newTarget) {
                d--;
            } else {
                a++;
            }
        }

        return result;
    }
    public List<List<Integer>> fourSum(int[] nums, int target, boolean useCubicComplexity) {
        if (!useCubicComplexity) {
            return this.fourSum(nums, target);
        }
        List<List<Integer>> result = new ArrayList<>();
        Set<String> uniqueResult = new HashSet<>();
        int n = nums.length;
        for (int i = n - 1; i > 2; i--) {
            for (int j = i - 1; j > 1; j--) {
                long newTarget = (long)target - (long)nums[i] - (long)nums[j];
                Set<Long> seen = new HashSet<>();

                for (int k = 0; k < j; k++) {
                    if (seen.contains(newTarget - nums[k])) {
                        Integer[] resultArr = new Integer[]{(int)newTarget - nums[k], nums[k], nums[j], nums[i]};
                        Arrays.sort(resultArr);
                        String resultHash = Arrays.stream(resultArr)
                               .map(String::valueOf) // Convert each integer to a string
                               .collect(Collectors.joining(":"));
                        if (!uniqueResult.contains(resultHash)) {
                            uniqueResult.add(resultHash);
                            result.add(Arrays.asList(resultArr));
                        }
                    }
                    seen.add((long)nums[k]);
                }
            }
        }

        return result;
    }
    public static void main(String[] args) {
        FourSum solution = new FourSum();

        // TestUtil.run(
        //     "Test Case #1",
        //     new int[][]{{-2,-1,1,2},{-2,0,0,2},{-1,0,0,1}},
        //     solution.fourSum(new int[]{1,0,-1,0,-2,2}, 0, true).stream()
        //     .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
        //     .toArray(int[][]::new)
        // );
        // TestUtil.run(
        //     "Test Case #2",
        //     new int[][]{{2,2,2,2}},
        //     solution.fourSum(new int[]{2,2,2,2,2}, 8, true).stream()
        //     .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
        //     .toArray(int[][]::new)
        // );
        // TestUtil.run(
        //     "Test Case #3",
        //     new int[][]{},
        //     solution.fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296, true).stream()
        //     .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
        //     .toArray(int[][]::new)
        // );


        TestUtil.run(
            "Test Case #4",
            new int[][]{{-2,-1,1,2},{-2,0,0,2},{-1,0,0,1}},
            solution.fourSum(new int[]{1,0,-1,0,-2,2}, 0, false).stream()
            .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new)
        );
        TestUtil.run(
            "Test Case #5",
            new int[][]{{2,2,2,2}},
            solution.fourSum(new int[]{2,2,2,2,2}, 8, false).stream()
            .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new)
        );
        TestUtil.run(
            "Test Case #6",
            new int[][]{},
            solution.fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296, false).stream()
            .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new)
        );

        TestUtil.run(
            "Test Case #7",
            new int[][]{{-4,0,1,2},{-1,-1,0,1}},
            solution.fourSum(new int[]{-1,0,1,2,-1,-4}, -1, false).stream()
            .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new)
        );

        TestUtil.run(
            "Test Case #8",
            new int[][]{{-3,-2,2,3},{-3,-1,1,3},{-3,0,0,3},{-3,0,1,2},{-2,-1,0,3},{-2,-1,1,2},{-2,0,0,2},{-1,0,0,1}},
            solution.fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0, false).stream()
            .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new)
        );
    }
}
