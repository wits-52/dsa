package utils;
import entities.TreeNode;

final public class BinaryTree {
    public static TreeNode fromArray(Integer[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        return insertLevelOrder(nodes, 0);
    }

    private static TreeNode insertLevelOrder(Integer[] nodes, int i) {
        if (i < nodes.length) {
            if (nodes[i] == null) {
                return null;
            }
            TreeNode root = new TreeNode(nodes[i]);
            root.left = insertLevelOrder(nodes, 2 * i + 1);
            root.right = insertLevelOrder(nodes, 2 * i + 2);
            return root;
        }
        return null;
    }

}
