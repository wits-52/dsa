package leetcode.daily;

public class TwoFurthestHousesWithDifferentColors {
    public int maxDistance(int[] colors) {
        int maxDistance = 0;

        int i = 0, j = colors.length - 1;

        while (i < j) {
            if (colors[i] != colors[j]) {
                maxDistance = Math.max(maxDistance, j - i);
                break;
            } else {
                i++;
            }
        }

        i = 0;
        while (i < j) {
            if (colors[i] != colors[j]) {
                maxDistance = Math.max(maxDistance, j - i);
                break;
            } else {
                j--;
            }
        }

        return maxDistance;
    }
}
