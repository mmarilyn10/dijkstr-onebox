package com.onebox.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import com.onebox.model.Graph;
import com.onebox.model.Node;

public class Dijkstr {
	private Graph graph;
	
	public Dijkstr(List<String> graphs) {
		graph = new Graph(graphs);
	}
	
	/**
	 * Permite calcular el costo desde el nodo inicial pasando por todos los nodos indicados en estricto orden
	 * @param origen Origen
	 * @param destinate Destino (si existe solo un nodo)
	 * @param nodos Camino (si existe mas de un nodo en el camino)
	 * @return Costo para hacer el recorrido
	 */
	public String calculate(String origen, String destinate, String... nodos) {
		try {
			List<String> destination = new ArrayList<>();
			destination.add(destinate);
			destination.addAll(Arrays.asList(nodos));
			return calculate(origen, destination).toString();
		}catch(NoSuchElementException e) {
			return "NO SUCH ROUTE";
		}
	}
	
	/**
	 * Permite calcular el costo desde el nodo inicial pasando por todos los nodos indicados en estricto orden
	 * @param origin Nodo inicial
	 * @param destination Nodos por donde debe pasar
	 * @return Costo para hacer el recorrido
	 */
	private Integer calculate(String origin, List<String> destination) {
		if(destination.isEmpty()) return 0;
		
		Node start = graph.getNode(origin.charAt(0));
		String destinatation = destination.get(0);
		Node end = graph.getNode(destinatation.charAt(0));
		
		destination.remove(0);		
		return calculate(start, end) + calculate(destinatation, destination);
	}
	
	/**
	 * Permite calcular el costo de un nodo incial a un nodo final
	 * @param origen Origen 
	 * @param destino Destino
	 * @return Costo de hacer el recorrido
	 * @throws NoSuchElementException
	 */
	private int calculate(Node origin, Node destination) throws NoSuchElementException {
		graph.getNodes().forEach(n -> n.setDistance(Integer.MAX_VALUE));
		Set<Node> route = calculateRouteFromOrigen(origin);
		
		if(route.contains(destination)) {		
			return route.stream().filter(n -> n.equals(destination)).findFirst().get().getDistance();
		}
		throw new NoSuchElementException();
	}
	
	/**
	 * Permite calcular todos los nodos a los que puedo llegar desde el origen
	 * @param origin Nodo origen
	 * @return Lista que contiene los nodos a los que puedo llegar con su costo
	 */
	private Set<Node> calculateRouteFromOrigen(Node origin) {
	    origin.setDistance(0);
	 
	    Set<Node> settledNodes = new HashSet<Node>();
	    Set<Node> unsettledNodes = new HashSet<Node>();
	    Set<Node> route = new HashSet<>();
	    
	    unsettledNodes.add(origin);
	 
	    while (unsettledNodes.size() != 0) {
	        Node currentNode = getLowestDistanceNode(unsettledNodes);
	        unsettledNodes.remove(currentNode);
	        for (Entry<Node, Integer> adjacencyPair: 
	          currentNode.getAdjacentNodes().entrySet()) {
	            Node adjacentNode = adjacencyPair.getKey();
	            Integer edgeWeight = adjacencyPair.getValue();
	            if (!settledNodes.contains(adjacentNode)) {
	                calculateMinimumDistance(adjacentNode, edgeWeight, currentNode, route);
	                unsettledNodes.add(graph.getNode(adjacentNode.getName()));
	            }
	        }
	        settledNodes.add(currentNode);
	    }
	    
	    return route;
	}
	
	/**
	 * Distancia mas corta entre los nodos no visitados
	 * @param unsettledNodes Lista de nodos no visitados
	 * @return Devuelve el nodo con el menos costo
	 */
	private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
	    Node lowestDistanceNode = null;
	    int lowestDistance = Integer.MAX_VALUE;
	    for (Node node: unsettledNodes) {
	        int nodeDistance = node.getDistance();
	        if (nodeDistance < lowestDistance) {
	            lowestDistance = nodeDistance;
	            lowestDistanceNode = node;
	        }
	    }
	    return lowestDistanceNode;
	}
	
	/**
	 * Calcula el costo minimo entre el camino recorrido y anteriores caminos para llegar al nodo
	 * @param evaluationNode Nodo final
	 * @param edgeWeigh Costo previo
	 * @param sourceNode Nodo inicial
	 * @param route Lista de opciones
	 */
	private void calculateMinimumDistance(Node evaluationNode,
	  Integer edgeWeigh, Node sourceNode, Set<Node> route) {
	    Integer sourceDistance = sourceNode.getDistance();
	    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
	        evaluationNode.setDistance(sourceDistance + edgeWeigh);
	        route.add(evaluationNode);
	    }
	}
	
}
