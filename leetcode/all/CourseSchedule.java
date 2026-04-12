package leetcode.all;

import java.util.*;

public class CourseSchedule {
    public boolean findCycle(int[] hasCycle, List<List<Integer>> adjList, int node, Set<Integer> visited) {
        if (hasCycle[node] != 0) {
            return hasCycle[node] == 1;
        }
        if (visited.contains(node)) {
            hasCycle[node] = 1;
            return true;
        }
        visited.add(node);

        List<Integer> nextCourses = adjList.get(node);
        boolean cycleFound = false;

        for (int i = 0; i < nextCourses.size(); i++) {
            cycleFound = cycleFound || this.findCycle(hasCycle, adjList, nextCourses.get(i), visited);

            if (cycleFound) {
                hasCycle[node] = 1;
                return true;
            }
        }

        hasCycle[node] = 2;
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 0 -> not calculated, 1 -> has cycle, 2 -> no cycle
        int[] hasCycle = new int[numCourses];
        Set<Integer> visited = new HashSet<>();

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // completing coursenum unlocks list of courses
        for (int[] edge: prerequisites) {
            adjList.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            visited.clear();
            if (hasCycle[i] == 0) {
                boolean cycleFound = this.findCycle(hasCycle, adjList, i, visited);

                if (cycleFound) return false;
            } else if (hasCycle[i] == 1) {
                return false;
            }
        }
        // no cycle found after visiting all courses
        return true;
    
    }
    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();

        System.out.println(solution.canFinish(5, new int[][]{{1,4}, {2,4}, {3,1}, {3,2}}));
        System.out.println(solution.canFinish(3, new int[][]{{0,1}, {0,2}, {1,2}}));

        System.out.println(solution.canFinish(8, new int[][]{{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}}));
    }
}
