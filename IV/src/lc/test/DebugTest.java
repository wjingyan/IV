package lc.test;

import static org.junit.Assert.*;
import lc.Debug;

import org.junit.Before;
import org.junit.Test;

public class DebugTest {
	Debug tester;
	
	int a;
	int b;
	int expectedRes;
	
	@Before
	public void initObjects() {
		a = 1;
		b = 1;
		expectedRes = 3;
	}

	@Test
	public void test() {
		Debug tester = new Debug();
		assertEquals("1 + 1 must be 2", expectedRes, tester.sum(a, b));
	}

}
