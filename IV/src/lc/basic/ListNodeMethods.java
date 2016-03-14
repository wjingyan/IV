package lc.basic;

public class ListNodeMethods {
	public static ListNode createLinkedListFromArray(int[] vals) {
		ListNode head = new ListNode(vals[0]);
		ListNode current = head;
		ListNode prev = current;
		for (int i = 1; i < vals.length; i++) {
			current = new ListNode(vals[i]);
			prev.next = current;
		}
		return head;
	}
}
