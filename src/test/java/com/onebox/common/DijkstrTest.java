package com.onebox.common;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DijkstrTest {
	
	private List<String> graphs = Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");

	Dijkstr algorithm; 
	
	@Before
	public void fillGraph() {
		algorithm = new Dijkstr(graphs);
	}
	
	@Test
	public void Output1() {
		String result = algorithm.calculate("A", "B", "C");
		assertEquals("9", result);
		System.out.println("Ouput #1: " + result);
	}
	
	@Test
	public void Output2() {
		String result = algorithm.calculate("A", "D");
		assertEquals("5", result);
		System.out.println("Ouput #2: " + result);
	}
	
	@Test
	public void Output3() {
		String result = algorithm.calculate("A", "D", "C");
		assertEquals("13", result);
		System.out.println("Ouput #3: " + result);
	}
	
	@Test
	public void Output4() {
		String result = algorithm.calculate("A", "E", "B", "C", "D");
		assertEquals("22", result);
		System.out.println("Ouput #4: " + result);
	}
	
	@Test
	public void Output5() {
		String result = algorithm.calculate("A", "E", "B", "C", "D");
		assertEquals("22", result);
		System.out.println("Ouput #5: " + result);
	}
	
	@Test
	public void Output6() {
		String result = algorithm.calculate("A", "D", "C", "A");
		assertEquals("NO SUCH ROUTE", result);
		System.out.println("Ouput #6: " + result);
	}
	
}
