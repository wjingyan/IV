package algo.tree;

import lc.basic.TreeNode;

public class RemoveBSTNode {

	private void removeBSTNode(TreeNode node, TreeNode root) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			TreeNode parent = findParent(node, root);
			if (node == parent.left) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		} else if (node.left != null && node.right != null) {
			TreeNode nextSuccessor = getMin(node.right);
			node.val = nextSuccessor.val;
			removeBSTNode(nextSuccessor, root);
		} else if (node.left == null) {
			node.val = node.right.val;
			removeBSTNode(node.right, root);
		} else {
			node.val = node.left.val;
			removeBSTNode(node.left, root);
		}
	}

	private TreeNode findParent(TreeNode target, TreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.left == target || root.right == target) {
			return root;
		} else {
			TreeNode left = findParent(target, root.left);
			if (left != null && (left.left == target || left.right == target)) {
				return left;
			}
			TreeNode right = findParent(target, root.right);
			if (right != null && (right.left == target || right.right == target)) {
				return right;
			}
		}
		return null;
	}

	// Get next Successor
	private TreeNode getMin(TreeNode node) {
		if (node == null) {
			return null;
		}
		if (node.left == null) {
			return node;
		} else {
			return getMin(node.left);
		}
	}

	public static void main (String[] args) {
		RemoveBSTNode i = new RemoveBSTNode();

		//case 1: remove root right child 20
		TreeNode root1 = Utils.preOrderDeserializer("30 10 50 # # # 20 45 # # 35 # #");
		TreeNode node1 = root1.right;
		i.removeBSTNode(node1, root1);
		System.out.println(Utils.preOrderSerializer(root1));

		//case 2: remove root left child 10
		TreeNode root2 = Utils.preOrderDeserializer("30 10 50 # # # 20 45 # # 35 # #");
		TreeNode node2 = root2.left;
		i.removeBSTNode(node2, root2);
		System.out.println(Utils.preOrderSerializer(root2));

		//case 3: remove level 2 left most child 50
		TreeNode root3 = Utils.preOrderDeserializer("30 10 50 # # # 20 45 # # 35 # #");
		TreeNode node3 = root3.left.left;
		i.removeBSTNode(node3, root3);
		System.out.println(Utils.preOrderSerializer(root3));
	}

}
