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
	public void testIntegrante3() {
		DummyDesign design = new DummyDesign();
		int result = design.integrante3();
		assertEquals(result,4);
	}

	@Test
	public void testIntegrante2() {
		DummyDesign design = new DummyDesign();
		int result = design.integrante2();
		assertEquals(result,2);
		
	}
	
	@Test
	public void testIntegrante1 () {
		DummyDesign design = new DummyDesign();
		int result = design.integrante1();
		assertEquals(result,1);
	}
}
