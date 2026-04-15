package leetcode.all;

public class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int leftMax = height[0], rightMax = height[height.length - 1], l = 0, r = height.length - 1, currentLevel = Math.min(rightMax, leftMax);

        int collectedWater = (r - l - 1) * currentLevel;
        while (l < r - 1) {
            if (height[l] <= height[r]) {
                l++;
                leftMax = Math.max(leftMax, height[l]);

                collectedWater -= Math.min(height[l], currentLevel);
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);

                collectedWater -= Math.min(height[r], currentLevel);
            }
            int newLevel = Math.min(rightMax, leftMax);

            if (currentLevel != newLevel) {
                collectedWater += (r - l - 1) * (newLevel - currentLevel);
                currentLevel = newLevel;
            }
        }
        
        return collectedWater;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();

        System.out.println(solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6
        System.out.println(solution.trap(new int[]{4,2,0,3,2,5})); // 9
        System.out.println(solution.trap(new int[]{1})); // 0
    }
}
