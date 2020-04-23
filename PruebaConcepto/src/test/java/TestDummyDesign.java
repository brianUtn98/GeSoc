import static org.junit.Assert.*;

import org.junit.Test;

public class TestDummyDesign {

	@Test
	public void testIntegrante5 () {

		DummyDesign desing = new DummyDesign();
		int result = desing.integrante5();
		assertEquals(result,5);
	}

	@Test
	public void testIntegrante4 () {
		DummyDesign design = new DummyDesign();
		int result = design.integrante4();
		assertEquals(result,4);
	}

	@Test
	public void testIntegrante1 () {
		DummyDesign design = new DummyDesign();
		int result = design.integrante1();
		assertEquals(result,1);
	}
}
