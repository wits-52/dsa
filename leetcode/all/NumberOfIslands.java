package leetcode.all;

public class NumberOfIslands {
    private void visitIsland(char[][] grid, int i, int j, int[][] visited) {
        int m = grid.length, n = grid[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == 1 || grid[i][j] == '0') {
            return;
        }

        visited[i][j] = 1;

        visitIsland(grid, i+1, j, visited);
        visitIsland(grid, i-1, j, visited);
        visitIsland(grid, i, j+1, visited);
        visitIsland(grid, i, j-1, visited);
    }
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, islands = 0;

        int[][] visited = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0 && grid[i][j] == '1') {
                    this.visitIsland(grid, i, j, visited);
                    islands++;
                }
            }
        }

        return islands;
    }
}
