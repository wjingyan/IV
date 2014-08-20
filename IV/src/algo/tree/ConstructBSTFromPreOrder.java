package algo.tree;

import lc.basic.TreeNode;

public class ConstructBSTFromPreOrder {

	public static void main(String[] args) {
		ConstructBSTFromPreOrder i = new ConstructBSTFromPreOrder();
		int[] pre = {10, 5, 1, 7, 40, 50};
		Utils.printInorder(i.constructTreeFromPre(pre));
	}

	public TreeNode constructTreeFromPre(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		index = 0;
		return constructTreeFromPreHelper(pre, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	int index;

	private TreeNode constructTreeFromPreHelper(int[] pre, int min, int max) {
		if (min >= max || index >= pre.length) {
			return null;
		}
		TreeNode node;
		if (pre[index] > min && pre[index] < max) {
			node = new TreeNode(pre[index]);
			index++;
		} else {
			return null;
		}
		node.left = constructTreeFromPreHelper(pre, min, node.val);
		node.right = constructTreeFromPreHelper(pre, node.val, max);
		return node;
	}
}
