package online.ms20151114;

public class ReverseSentence {
	public static String reverse(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] arr = input.toCharArray();
		int charStart = 0;
		while (arr[charStart] == ' ') {
			charStart++;
		}
		int charEnd = arr.length - 1;
		while (arr[charEnd] == ' ') {
			charEnd--;
		}
		reverseHelper(arr, charStart, charEnd);
		int startOfWord = charStart;
		int endOfWord = charStart;
		while (endOfWord <= charEnd + 1) {
			if (endOfWord == charEnd + 1 || arr[endOfWord] == ' ') {
				reverseHelper(arr, startOfWord, endOfWord - 1);
				startOfWord = endOfWord + 1;
			}
			endOfWord++;
		}
		return new String(arr);
	}
	
	private static void reverseHelper(char[] arr, int start, int end) {
		while (start < end) {
			char tmp = arr[start];
			arr[start] = arr[end];
			arr[end] = tmp;
			start++;
			end--;
		}
	}
}
