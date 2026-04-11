package leetcode.all;

public class CapacityToShipPackagesWithinDDays {
    private int findMinDays(int[] weights, int capacity) {
        int days = 1, shippedIndex = 0;

        int sum = 0;
        while(shippedIndex < weights.length) {
            if (sum + weights[shippedIndex] > capacity) {
                days++;
                sum = 0;
            }
            sum += weights[shippedIndex++];
        }

        return days;
    }
    private int shipWithinDays(int[] weights, int days) {
        int max = 0, sum = 0;

        for (int w: weights) {
            max = Math.max(max, w);
            sum += w;
        }

        int start = max, end = sum;

        while (start < end) {
            int mid = (start + end) / 2;

            int daysTaken = this.findMinDays(weights, mid);

            if (daysTaken > days) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
    public static void main(String[] args) {
        CapacityToShipPackagesWithinDDays solution = new CapacityToShipPackagesWithinDDays();

        System.out.println(
            solution.shipWithinDays(new int[]{1,2,3,1,1}, 4)
        );
    }
}
