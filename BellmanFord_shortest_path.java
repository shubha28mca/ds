import java.util.*;

/**
 * Bellman-Ford Algorithm Implementation
 *
 * ✅ Approach:
 * - Step 1: Initialize distance to all vertices as ∞ (except source = 0)
 * - Step 2: Relax all edges V-1 times
 *     - For each edge (u -> v), if dist[u] + weight < dist[v], update dist[v]
 * - Step 3: Check for negative weight cycles by running one more relaxation.
 *     - If any distance gets reduced, there’s a negative cycle
 *
 * ⏱ Time Complexity: O(V * E)
 * - We iterate over all E edges V-1 times + 1 final cycle check
 *
 * 🧠 Space Complexity: O(V)
 * - We store distance for each vertex
 */
class Edge {
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

public class BellmanFord {

    /**
     * Runs the Bellman-Ford algorithm on a graph defined by edge list
     *
     * @param edges       List of edges (u -> v with weight)
     * @param numVertices Number of vertices in the graph
     * @param source      Starting node
     * @return Map of shortest distances from source, or null if a negative cycle exists
     */
    public static Map<Integer, Integer> bellmanFord(List<Edge> edges, int numVertices, int source) {
        Map<Integer, Integer> dist = new HashMap<>();

        // Initialize distances to all vertices as infinity
        for (int i = 0; i < numVertices; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }
        dist.put(source, 0); // Distance to source is 0

        // Relax all edges V - 1 times
        for (int i = 0; i < numVertices - 1; i++) {
            for (Edge edge : edges) {
                int u = edge.from;
                int v = edge.to;
                int wt = edge.weight;

                if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + wt < dist.get(v)) {
                    dist.put(v, dist.get(u) + wt);
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge edge : edges) {
            int u = edge.from;
            int v = edge.to;
            int wt = edge.weight;

            if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + wt < dist.get(v)) {
                System.out.println("Graph contains a negative weight cycle!");
                return null;
            }
        }

        return dist;
    }

    // Sample usage
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        int numVertices = 5;

        // Sample directed weighted graph (can include negative weights)
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 4, -4));
        edges.add(new Edge(2, 3, -3));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 0, 2));
        edges.add(new Edge(4, 3, 7));

        int source = 0;
        Map<Integer, Integer> distances = bellmanFord(edges, numVertices, source);

        if (distances != null) {
            System.out.println("Shortest distances from source " + source + ":");
            for (int v = 0; v < numVertices; v++) {
                System.out.println("To " + v + " = " + (distances.get(v) == Integer.MAX_VALUE ? "∞" : distances.get(v)));
            }
        }
    }
}
/*
Shortest distances from source 0:
To 0 = 0
To 1 = 2
To 2 = 7
To 3 = 4
To 4 = -2

*/
