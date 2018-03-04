package com.onebox;
import java.util.Arrays;
import java.util.List;

import com.onebox.common.Dijkstr;

public class Route {

	public static List<String> graphs = Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
	
	public static void main(String[] args) {
		
		//1.- Se configura el grafo
		Dijkstr algorithm = new Dijkstr(graphs);
		
		//2.- Se ejecutan los test 
		System.out.println(String.format("Ouput #1: %s", algorithm.calculate("A", "B", "C")));
		System.out.println(String.format("Ouput #2: %s", algorithm.calculate("A", "D")));
		System.out.println(String.format("Ouput #3: %s", algorithm.calculate("A", "D", "C")));
		System.out.println(String.format("Ouput #4: %s", algorithm.calculate("A", "E", "B", "C", "D")));
		System.out.println(String.format("Ouput #5: %s", algorithm.calculate("A", "E", "B", "C", "D")));
		System.out.println(String.format("Ouput #6: %s", algorithm.calculate("A", "D", "C", "A")));
	}

}
