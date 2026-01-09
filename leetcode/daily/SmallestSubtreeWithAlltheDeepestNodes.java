package leetcode.daily;

import entities.TreeNode;

public class SmallestSubtreeWithAlltheDeepestNodes {
    int[] nodeHeight = new int[501];
    TreeNode[] highestNodeWithEqualHeight = new TreeNode[501];
    TreeNode result;
    private int findMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            this.nodeHeight[root.val] = 1;
            this.highestNodeWithEqualHeight[root.val] = root;
            return 1;
        }

        int leftHeight = this.findMaxDepth(root.left);
        int rightHeight = this.findMaxDepth(root.right);

        int height = 1 + Math.max(leftHeight, rightHeight);
        this.nodeHeight[root.val] = height;

        if (leftHeight == rightHeight) {
            highestNodeWithEqualHeight[root.val] = root;
        } else if (leftHeight > rightHeight) {
            highestNodeWithEqualHeight[root.val] = highestNodeWithEqualHeight[root.left.val];
        } else {
            highestNodeWithEqualHeight[root.val] = highestNodeWithEqualHeight[root.right.val];
        }

        return height;

    }
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        this.findMaxDepth(root);

        return this.highestNodeWithEqualHeight[root.val];
    }
}