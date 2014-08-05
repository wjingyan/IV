package lc;

public class Debug {
	
	public void merge(int A[], int m, int B[], int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int i = m + n - 1;
        while (i >= 0 && p1 >= 0 && p2 >= 0) {
            A[i--] = (A[p1] > A[p2]) ? A[p1--] : A[p2--];
        }
        if (p1 <= 0 && p2 >= 0) {
            System.arraycopy(B, 0, A, 0, p2 + 1);
        }
    }
	
	public int sum(int a, int b) {
		return a + b;
	}

}
