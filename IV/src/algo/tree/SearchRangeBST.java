package algo.tree;

import lc.basic.TreeNode;
/*
 * http://www.geeksforgeeks.org/print-bst-keys-in-the-given-range/
 */

public class SearchRangeBST {
	/* The functions prints all the keys which in the given range [k1..k2].
	The function assumes than k1 < k2 */
	public void printRange(TreeNode root, int start, int end) {
		if (root == null) {
			return;
		}
		if (root.val > start) {
			printRange(root.left, start, end);
		}
		if (root.val <= end && root.val >= start) {
			System.out.print(root.val + " ");
		}
		if (root.val < end) {
			printRange(root.right, start, end);
		}
	}
	
	public static void main(String[] args) {
		SearchRangeBST i = new SearchRangeBST();
		TreeNode r = Utils.preOrderDeserializer("20 8 4 # # 12 # # 22 # #");
		i.printRange(r, 8, 10);
	}
}
