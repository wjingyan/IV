package algo.tree;

import lc.basic.TreeNode;

public class Utils {
	public static void printInorder(TreeNode root) {
		if (root == null) {
			return;
		}
		printInorder(root.left);
		System.out.print(root.val + " ");
		printInorder(root.right);
	}
}
