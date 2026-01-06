package leetcode.daily;

import java.util.LinkedList;
import java.util.Queue;

import entities.TreeNode;

import utils.BinaryTree;
import utils.TestUtil;


public class MaximumLevelSumOfBinaryTree {
    public int maxLevelSum(TreeNode root) {
        int maxSumlevel = 1, maxSum = root.val, currentLevel = 1;

        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.offer(root);

        while (bfs.size() > 0) {
            int sum = 0, thisLevelSize = bfs.size();

            for (int i = 0; i < thisLevelSize; i++) {
                TreeNode head = bfs.poll();

                sum += head.val;
                if (head.left != null) {
                    bfs.offer(head.left);
                }
                if (head.right != null) {
                    bfs.offer(head.right);
                }
            }

            if (sum > maxSum) {
                maxSum = sum;
                maxSumlevel = currentLevel;
            }
            currentLevel++;
        }

        return maxSumlevel;
    }

    public static void main(String[] args) {
        MaximumLevelSumOfBinaryTree solution = new MaximumLevelSumOfBinaryTree();

        TestUtil.run(
            "Test Case #1",
            2,
            solution.maxLevelSum(BinaryTree.fromArray(new Integer []{ 1, 7, 0, 7, -8, null, null }))
        );

        TestUtil.run(
            "Test Case #2",
            2,
            solution.maxLevelSum(BinaryTree.fromArray(new Integer []{ 989, null, 10250, 98693, -89388, null, null, null, -32127 }))
        );

        TestUtil.run(
            "Test Case #3",
            1,
            solution.maxLevelSum(BinaryTree.fromArray(new Integer []{ 0 }))
        );
    }
}
