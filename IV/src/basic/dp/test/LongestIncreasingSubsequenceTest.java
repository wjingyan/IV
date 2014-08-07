package basic.dp.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import basic.dp.LongestIncreasingSubsequence;

public class LongestIncreasingSubsequenceTest {
	@Test
	public void test() {
		LongestIncreasingSubsequence tester = new LongestIncreasingSubsequence();
		int[] A = {5, 4, 1, 2, 3};
		assertEquals("LIS of [5, 4, 1, 2, 3] is 3", 3, tester.longestIncreasingSubsequence(A));
		int[] B = {4, 2, 4, 5, 3, 7};
		assertEquals("LIS of [4, 2, 4, 5, 3, 7] is 4", 4, tester.longestIncreasingSubsequence(B));
	}
}
