package com.onebox.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Graph {
	
	private Set<Node> nodes = new HashSet<Node>();

	public Graph(List<String> graph) {
		fillGraph(graph);
	}
	
	private void fillGraph(List<String> graph) {
		for (String route : graph) {
			Node origin = getNode(route.charAt(0));
			Node adjacent = getNode(route.charAt(1));
			origin.addDestination(adjacent, Integer.parseInt(String.valueOf(route.charAt(2))));
			nodes.add(origin);
		}
	}

	public Node getNode(char name) {
		Node node = new Node(name);
		if(!nodes.add(node)) {
			return nodes.stream().filter(n -> n.equals(node)).findFirst().get();
		}
		
		return node;
	}
}