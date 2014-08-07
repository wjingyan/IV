package basic.dp;

public class LongestIncreasingSubsequence {
	public int longestIncreasingSubsequence(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] lis = new int[nums.length];
		lis[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] >= nums[j]) {
					lis[i] = Math.max(lis[i], lis[j] + 1);
				}
			}
		}
		return lis[nums.length - 1];
    }
}
