package leetcode.all;

import entities.TreeNode;

public class BinaryTreeMaximumPathSum {
    private int maxGlobalSum = 0;

    private int maxPathSumWithNodeAsTerminal(TreeNode node) {
        if (node == null) return 0;

        int leftSum = this.maxPathSumWithNodeAsTerminal(node.left);
        int rightSum = this.maxPathSumWithNodeAsTerminal(node.right);

        int sum = node.val;
        this.maxGlobalSum = Math.max(this.maxGlobalSum, sum);

        if (leftSum <= 0 && rightSum <= 0) {
            return sum;
        }
        sum += Math.max(rightSum, leftSum);
        this.maxGlobalSum = Math.max(this.maxGlobalSum, sum);

        this.maxGlobalSum = Math.max(this.maxGlobalSum, sum + Math.min(rightSum, leftSum));

        return sum;
    }

    public int maxPathSum(TreeNode root) {
        this.maxGlobalSum = root.val;

        this.maxPathSumWithNodeAsTerminal(root);

        return this.maxGlobalSum;
    }
}
