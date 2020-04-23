import static org.junit.Assert.*;

import org.junit.Test;

public class TestDummyDesign {

	@Test
	public void testIntegrante5 () {
		
		DummyDesign desing = new DummyDesign();
		int result = desing.integrante5();
		assertEquals(result,5);
	}

}
