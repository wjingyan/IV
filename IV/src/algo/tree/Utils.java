package algo.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import lc.basic.TreeNode;

public class Utils {

	public static void main(String[] args) {
		TreeNode t1 = preOrderDeserializer("30 10 50 # # # 20 45 # # 35 # #");
		//printInorder(t1);
		StringBuilder sb = new StringBuilder();
		preOrderSerializer(t1, sb);
		System.out.println(sb.toString());
	}

	public static void printInorder(TreeNode root) {
		if (root == null) {
			return;
		}
		printInorder(root.left);
		System.out.print(root.val + " ");
		printInorder(root.right);
	}

	public static void preOrderSerializer(TreeNode node, StringBuilder str) {
		if (node == null) {
			str.append("# ");
			return;
		}
		str.append(node.val + " ");
		preOrderSerializer(node.left, str);
		preOrderSerializer(node.right, str);
	}

	public static TreeNode preOrderDeserializer(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		Queue<String> list = new LinkedList<String>(Arrays.asList(str.split(" ")));
		return preOrderDeserializerHelper(list);
	}

	public static TreeNode preOrderDeserializerHelper(Queue<String> list) {
		String valStr = list.poll();
		if (valStr.equals("#")) {
			return null;
		} else {
			int val = Integer.parseInt(valStr);
			TreeNode node = new TreeNode(val);
			node.left = preOrderDeserializerHelper(list);
			node.right = preOrderDeserializerHelper(list);
			return node;
		}
	}
}
