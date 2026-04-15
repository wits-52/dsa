package leetcode.all;

import java.util.*;

public class KClosestPointsToOrigin {
    private int[][] kClosest(int[][] points, int k) {
        int[][] kClosestPoints = new int[k][2];

        if (k == points.length) {
            return points;
        }


        PriorityQueue<int[]> closestKPointsIndexes = new PriorityQueue<>(
            (a, b) -> Long.compare(
                (long)(b[0] * b[0]) + (long)(b[1] * b[1]),
                (long)(a[0] * a[0]) + (long)(a[1] * a[1])
            )
        );

        for (int i = 0; i < points.length; i++) {
            closestKPointsIndexes.add(points[i]);

            if (closestKPointsIndexes.size() > k) {
                closestKPointsIndexes.poll();
            }
        }

        int counter = 0;
        while(!closestKPointsIndexes.isEmpty() && counter < k) {
            int[] point = closestKPointsIndexes.poll();

            kClosestPoints[counter++] = point;
        }
        return kClosestPoints;
    }
    public static void main(String[] args) {
        KClosestPointsToOrigin solution = new KClosestPointsToOrigin();

        System.out.print(solution.kClosest(new int[][]{}, 0));
    }
}
