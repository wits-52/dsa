package tuf.sde_sheet.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.TestUtil;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();

            row.add(1);
            for (int i = 1; i <= rowNum; i++) {
                if (i == rowNum) {
                    row.add(1);
                } else {
                    row.add(result.get(rowNum - 1).get(i) + result.get(rowNum - 1).get(i-1));
                }
            }

            result.add(row);
        }

        return result;
    }
    public static void main(String[] args) {
        PascalTriangle solution = new PascalTriangle();

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(new Integer[]{1}));
        expected.add(Arrays.asList(new Integer[]{1, 1}));
        expected.add(Arrays.asList(new Integer[]{1,2,1}));
        expected.add(Arrays.asList(new Integer[]{1,3,3,1}));
        expected.add(Arrays.asList(new Integer[]{1,4,6,4,1}));

        TestUtil.run(
            "Test Case #1",
            expected, 
            solution.generate(5)
        );


        expected = new ArrayList<>();
        expected.add(Arrays.asList(new Integer[]{1}));

        TestUtil.run(
            "Test Case #2",
            expected, 
            solution.generate(1)
        );
    }
}
