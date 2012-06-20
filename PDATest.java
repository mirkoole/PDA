import static org.junit.Assert.*;

import org.junit.Test;

public class PDATest {

	@Test
	public void testAccept() {
		PDA p = new PDA();
		assertTrue(p.accept(""));
		assertTrue(p.accept("01"));
		assertTrue(p.accept("000111"));
		assertTrue(p.accept("11110000"));
		assertTrue(p.accept("11111111111110000000000000"));
		assertTrue(p.accept("00000000000001111111111111"));		
		assertFalse(p.accept("0000001111"));
		assertFalse(p.accept("000000"));
		assertFalse(p.accept("11111111"));
	}
	@Test
	public void testStackContent() {
		PDA p = new PDA();
		assertEquals("Testing getStackContent-method", p.getStackContent("011111"),new String("[#]"));
		assertEquals("Testing getStackContent-method", p.getStackContent("00001"),new String("[#, A, A, A]"));
		assertEquals("Testing getStackContent-method", p.getStackContent("001"),new String("[#, A]"));
		assertEquals("Testing getStackContent-method", p.getStackContent("0001"),new String("[#, A, A]"));
	}

	@Test
	public void testGetNextState() {
	}
}
