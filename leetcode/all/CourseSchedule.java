package leetcode.all;

import java.util.*;

public class CourseSchedule {
    Map<Integer, Boolean> hasCycle = new HashMap<>();

    public boolean detectCycle(List<List<Integer>> adjList, int node, Set<Integer> inCycle) {
        if (hasCycle.containsKey(node)) return hasCycle.get(node);
        if (inCycle.contains(node)) {
            hasCycle.put(node, true);
            return true;
        } else {
            inCycle.add(node);
        }

        List<Integer> connectedNodes = adjList.get(node);

        boolean cycleFound = false;

        for (int i = 0; i < connectedNodes.size(); i++) {
            cycleFound = cycleFound || this.detectCycle(adjList, connectedNodes.get(i), inCycle);
        }
        if (!cycleFound) {
            inCycle.remove(node);
        }
        hasCycle.put(node, cycleFound);

        return cycleFound;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        this.hasCycle.clear();

        for (int i = 0; i <= numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge: prerequisites) {
            adjList.get(edge[0]).add(edge[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            Set<Integer> inCycle = new HashSet<>();

            if (!hasCycle.containsKey(i)) {
                boolean cycleFound = this.detectCycle(adjList, i, inCycle);

                if (cycleFound) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();

        System.out.println(solution.canFinish(5, new int[][]{{1,4}, {2,4}, {3,1}, {3,2}}));
        System.out.println(solution.canFinish(3, new int[][]{{0,1}, {0,2}, {1,2}}));

        System.out.println(solution.canFinish(8, new int[][]{{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}}));
    }
}
