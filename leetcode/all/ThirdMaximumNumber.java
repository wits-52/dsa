package leetcode.all;

public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        int largest = nums[0];

        for (int n: nums) {
            largest = Math.max(n, largest);
        }

        int secondLargest = -2147483648;

        for (int n: nums) {
            if (n < largest) {
                secondLargest = Math.max(n, secondLargest);
            }
        }
        long thirdLargest = -21474836489L;
        for (int n: nums) {
            if (n < secondLargest) {
                thirdLargest = Math.max(n, thirdLargest);
            }
        }

        if (thirdLargest == -21474836489L) {
            return largest;
        }

        return (int)thirdLargest;
    }
}
