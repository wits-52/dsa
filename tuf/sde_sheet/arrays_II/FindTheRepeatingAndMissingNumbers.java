package tuf.sde_sheet.arrays_II;

import java.util.ArrayList;

import utils.TestUtil;

public class FindTheRepeatingAndMissingNumbers {
    ArrayList<Integer> findTwoElement(int arr[]) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] frequencies = new int[arr.length + 1];
        long sum = ((long)arr.length * ((long)arr.length + 1)) / 2l;

        for (int num: arr) {
            frequencies[num]++;
            sum -= num;
            if (frequencies[num] == 2) {
                result.add(num);
            }
        }

        result.add((int)sum+result.get(0));
        return result;
    }

    public static void main(String[] args) {
        FindTheRepeatingAndMissingNumbers solution = new FindTheRepeatingAndMissingNumbers();

        TestUtil.run("Test Case #1", new int[]{2,1}, solution.findTwoElement(new int[]{2,2}).stream().mapToInt(i -> i).toArray());
        TestUtil.run("Test Case #2", new int[]{3,2}, solution.findTwoElement(new int[]{1,3,3}).stream().mapToInt(i -> i).toArray());
        TestUtil.run("Test Case #3", new int[]{1,5}, solution.findTwoElement(new int[]{4, 3, 6, 2, 1, 1}).stream().mapToInt(i -> i).toArray());
    }
}
