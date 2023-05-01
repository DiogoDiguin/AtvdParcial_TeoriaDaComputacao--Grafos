package mygraph;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

class Edge {
  private float weight = 1;
  private Vertex from;
  private Vertex to;

  public Edge(Vertex from, Vertex to) {
    setFrom(from);
    setTo(to);
  }

  public Edge(Vertex from, Vertex to, float weight) {
    setFrom(from);
    setTo(to);
    setWeight(weight);
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public float getWeight() {
    return weight;
  }

  private void setFrom(Vertex from) {
    this.from = from;
  }

  public Vertex getFrom() {
    return from;
  }

  private void setTo(Vertex to) {
    this.to = to;
  }

  public Vertex getTo() {
    return to;
  }

  @Override
  public String toString() {
    return "[" + 
        this.weight + ", " + 
        this.from.getLabel() + ", " + 
        this.to.getLabel() + "]";
  }
}

class Vertex {
  private String label;
  private List<Edge> edges = new LinkedList<>();

  public Vertex(String label) {
    setLabel(label);
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void addEdge(Edge edge) {
    this.edges.add(edge);
  }

  public void removeEdge(Edge edge) {
    edges.remove(edge);
  }

  public List<Edge> getEdges() {
      return edges;
  } 

  @Override
  public String toString() {
    String ts = "[Label: " + label;
    for (Edge edge : this.edges) 
      ts += edge;
    return ts + "]";
  }
}

public class Graph {
  
  private List<Vertex> vertexes = new LinkedList<>();

  public Graph() {  }

  protected Vertex findVertex(String value) {
    try {
      return vertexes
          .stream()
          .filter(v -> v.getLabel().equals(value))
          .collect(Collectors.toList())
          .get(0);
    } catch(Exception e){
      return null;
    }
  }

  public void addVertex(String value) {
    var vertex = findVertex(value);
    if (vertex == null)
      vertexes.add(new Vertex(value));
  }

  public void removeVertex(String value) {
    var vertex = findVertex(value);
    if (vertex != null)
      vertexes.remove(vertex);
  }

  public void addEdge(String valA, String valB) {
    var va = findVertex(valA);
    var vb = findVertex(valB);
    va.addEdge(new Edge(va, vb));
    vb.addEdge(new Edge(vb, va));
  }

  public List<Vertex> neighbours(String value) {
      var v = findVertex(value);
      if (v == null)
          return null;
      List<Vertex> vs = new LinkedList<>();
      for(Edge e : v.getEdges())
          vs.add(e.getTo());
      
      return vs;
  }

  public boolean direct(String vA, String vB) {
      var v = findVertex(vA);
      if (v != null) {
        return v.getEdges()
            .stream()
            .filter(e -> e.getTo().getLabel().equals(vB))
            .count() == 1;
      }
      return false;
  }

  @Override
  public String toString() {
    var s = "";
    for(Vertex v : vertexes)
      s += v;
    return s;
  }

//Funções ATVD PARCIAL

public List<Vertex> caminho(Graph graph, String a, String b) {
    var va = graph.findVertex(a);
    var vb = graph.findVertex(b);
    if (va == null || vb == null) {
      return null;
    }
    Map<Vertex, Vertex> path = new HashMap<>();
    path.put(va, null);
    Queue<Vertex> queue = new LinkedList<>();
    queue.add(va);
    while (!queue.isEmpty()) {
      Vertex v = queue.remove();
      if (v.equals(vb)) {
        // Caminho encontrado, constrói a lista de vértices do caminho.
        List<Vertex> caminho = new LinkedList<>();
        Vertex current = vb;
        while (current != null) {
          caminho.add(0, current);
          current = path.get(current);
        }
        return caminho;
      }
      for (Edge e : v.getEdges()) {
        Vertex w = e.getTo();
        if (!path.containsKey(w)) {
          path.put(w, v);
          queue.add(w);
        }
      }
    }
    // Caminho não encontrado.
    return null;
  }

public static Graph uniao(Graph g1, Graph g2) {
	Graph result = new Graph();
    // Adiciona todos os vértices do G1 ao resultado.
    for (Vertex v : g1.vertexes) {
    	result.addVertex(v.getLabel());
    }
    // Adiciona todos os vértices do G2 que não estão no resultado.
    for (Vertex v : g2.vertexes) {
    	if (result.findVertex(v.getLabel()) == null) {
    		result.addVertex(v.getLabel());
    	}
    }
    // Adiciona todas as arestas do G1 ao resultado.
    for (Vertex v : g1.vertexes) {
    	for (Edge e : v.getEdges()) {
    		Vertex to = e.getTo();
    			if (result.findVertex(to.getLabel()) != null) {
    			result.addEdge(v.getLabel(), to.getLabel());
    		}
    	}
    }
    // Adiciona todas as arestas do G2 ao resultado.
    for (Vertex v : g2.vertexes) {
    	for (Edge e : v.getEdges()) {
    		Vertex to = e.getTo();
    			if (result.findVertex(to.getLabel()) != null) {
    			result.addEdge(v.getLabel(), to.getLabel());
    		}
    	}
    }
    return result;
}

public static Graph interseccao(Graph G1, Graph G2) {
    Graph interseccao = new Graph();
    for (Vertex v1 : G1.vertexes) {
        for (Vertex v2 : G2.vertexes) {
            if (v1.getLabel().equals(v2.getLabel())) {
                interseccao.addVertex(v1.getLabel());
                for (Edge e1 : v1.getEdges()) {
                    Vertex to1 = e1.getTo();
                    for (Edge e2 : v2.getEdges()) {
                        Vertex to2 = e2.getTo();
                        if (to1.getLabel().equals(to2.getLabel())) {
                            interseccao.addVertex(to1.getLabel());
                            interseccao.addEdge(v1.getLabel(), to1.getLabel());
                        }
                    }
                }
            }
        }
    }
    return interseccao;
}
//FIM ATVD PARCIAL

}//Final Classe