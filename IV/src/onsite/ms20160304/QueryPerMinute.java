package onsite.ms20160304;
/*
 * 3 Design a class that's able to give average qps in the past minute
 * This is an inaccurate method
 */

public class QueryPerMinute {
	int[] arr = new int[60];
	Integer lastIndex = null;
	
	//1, 1, 3, ... 62
	public void addQuery(int timeFromStart) {
		int index = timeFromStart % 60;
		if (lastIndex != null && lastIndex != index) {
			//clear all arry content from lastIndex to index
			for (int i = lastIndex + 1; i <= index; i++) {
				arr[i] = 0;
			}
		}
		arr[index]++;
		if (lastIndex != index) {
			lastIndex = index;
		}
	}
	
	public double getAvg() {
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		return (double)total / 60;
	}
}
