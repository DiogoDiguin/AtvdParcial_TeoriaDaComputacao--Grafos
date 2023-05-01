package mygraph;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    var graph = new Graph();
    var graph2 = new Graph();

    graph.addVertex("a");
    graph.addVertex("b");
    graph.addVertex("c");
    graph.addVertex("d");

    graph.addEdge("a", "b");
    graph.addEdge("a", "c");
    graph.addEdge("b", "c");
    graph.addEdge("a", "d");
    /**/
    graph2.addVertex("1");
    graph2.addVertex("2");
    graph2.addVertex("3");
    graph2.addVertex("4");

    graph2.addEdge("2", "4");
    graph2.addEdge("2", "3");
    graph2.addEdge("3", "1");
    graph2.addEdge("3", "4");
    
    System.out.println(graph);
    System.out.printf("\n");
    System.out.println(graph2);
    System.out.printf("\n");
    
    System.out.println("Vizinhos GRAFO 1 - Vértice A: " + graph.neighbours("a"));
    System.out.printf("\n");
    System.out.println("Vizinhos GRAFO 2 - Vértice 2: " + graph2.neighbours("2"));
    System.out.printf("\n");

    if (graph.direct("a", "c"))
        System.out.println("A e C estao ligados!!");
    
    if (!graph.direct("b", "d"))
        System.out.println("B e D NAO estao ligados!!");
    System.out.println("\n");
    
    if (graph2.direct("1", "3"))
        System.out.println("1 e 3 estao ligados!!");
    
    if (!graph2.direct("1", "4"))
        System.out.println("1 e 4 NAO estao ligados!!");
    System.out.printf("\n");
    
//ATVD PARCIAL
    
    //CAMINHO
    List<Vertex> caminho = graph.caminho(graph, "a", "b");
    if (caminho != null) {
        System.out.println("Caminho encontrado entre A e B: " + caminho);
    } else {
        System.out.println("Não existe caminho entre A e B no Grafo.");
    }
    System.out.printf("\n");
    
    //UNIÃO
	Graph uniao = Graph.uniao(graph, graph2);
	System.out.println("União de G1 e G2: " + uniao);
	System.out.printf("\n");
	
	//Teste Intersecção
	/*
	graph.addVertex("A");
	graph.addVertex("B");
	graph.addVertex("C");
	graph.addEdge("A", "B");
	graph.addEdge("B", "C");
	    
	graph2.addVertex("B");
	graph2.addVertex("C");
	graph2.addVertex("D");
	graph2.addEdge("B", "C");
	graph2.addEdge("C", "D");
	*/
	    
	 //INTERSECÇÃO
	 Graph interseccao = Graph.interseccao(graph, graph2);
	 System.out.println(interseccao);
 
 //FIM ATVD PARCIAL
    
    System.exit(0);
  }
}
