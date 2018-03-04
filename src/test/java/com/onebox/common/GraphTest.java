package com.onebox.common;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.onebox.model.Graph;
import com.onebox.model.Node;

public class GraphTest {
	
	private List<String> graphs = Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");

	private Graph graph;
	
	@Before
	public void fillGraph() {
		graph = new Graph(graphs);
	}
	
	@Test
    public void verifyAdjacentNodeA() {
       Set<Node> adjacent = graph.getNode('A').getAdjacentNodes().keySet();
       assertTrue(verifyAdjacent(adjacent, 'B'));
       assertTrue(verifyAdjacent(adjacent, 'D'));
       assertTrue(verifyAdjacent(adjacent, 'E'));       
    }
	
	@Test
    public void verifyAdjacentNodeB() {
       Set<Node> adjacent = graph.getNode('B').getAdjacentNodes().keySet();
       assertTrue(verifyAdjacent(adjacent, 'C'));
       assertTrue(verifyAdjacent(adjacent, 'C'));
    }
	
	@Test
    public void verifyAdjacentNodeC() {
       Set<Node> adjacent = graph.getNode('C').getAdjacentNodes().keySet();
       assertTrue(verifyAdjacent(adjacent, 'D')); 
       assertTrue(verifyAdjacent(adjacent, 'E')); 
    }
	
	@Test
    public void verifyAdjacentNodeD() {
       Set<Node> adjacent = graph.getNode('D').getAdjacentNodes().keySet();
       assertTrue(verifyAdjacent(adjacent, 'E')); 
    }
	
	@Test
    public void verifyAdjacentNodeE() {
       Set<Node> adjacent = graph.getNode('E').getAdjacentNodes().keySet();
       assertTrue(verifyAdjacent(adjacent, 'B')); 
    }
	
	private boolean verifyAdjacent(Set<Node> listAdjacent, char adjacent) {
		return listAdjacent.stream().filter(key -> key.equals(new Node(adjacent))).findFirst().isPresent();
	}
}
