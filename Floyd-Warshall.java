import java.util.*;

/**
 * Floyd-Warshall Algorithm using adjacency list representation.
 *
 * ✅ Approach:
 * - Step 1: Convert adjacency list to adjacency matrix.
 * - Step 2: Run Floyd-Warshall on the matrix.
 * - Step 3: Output the shortest distances.
 *
 * ⏱ Time Complexity: O(V^3)
 * 🧠 Space Complexity: O(V^2)
 */
public class FloydWarshallWithAdjList {

    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static final int INF = 1000000000;

    private Map<Integer, List<Edge>> adjList = new HashMap<>();

    // Add edge to graph
    public void addEdge(int from, int to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        // Ensure all nodes are initialized
        adjList.computeIfAbsent(to, k -> new ArrayList<>());
    }

    public void floydWarshall() {
        int n = adjList.size();
        int[][] dist = new int[n][n];

        // Step 1: Initialize the distance matrix
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Fill in edge weights
        for (int u : adjList.keySet()) {
            for (Edge e : adjList.get(u)) {
                dist[u][e.to] = e.weight;
            }
        }

        // Step 2: Run Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Step 3: Print the result
        System.out.println("All-pairs shortest paths:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Example usage
    public static void main(String[] args) {
        FloydWarshallWithAdjList graph = new FloydWarshallWithAdjList();

        // Example: 4 nodes (0 to 3)
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 0, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 1, 1);
        graph.addEdge(3, 2, 2);

        graph.floydWarshall();
    }
}
/*
output:
All-pairs shortest paths:
0 3 7 5 
2 0 6 4 
3 1 0 5 
5 3 2 0 



Explanation
| Row index (i) | Source node | Column index (j) | Destination node | Value = shortest distance from i to j |
| ------------- | ----------- | ---------------- | ---------------- | ------------------------------------- |
| 0             | 0           | 0                | 0                | 0 (distance to itself)                |
| 0             | 0           | 1                | 1                | 3                                     |
| 0             | 0           | 2                | 2                | 7                                     |
| 0             | 0           | 3                | 3                | 5                                     |
| 1             | 1           | 0                | 0                | 2                                     |
| 1             | 1           | 1                | 1                | 0                                     |
| 1             | 1           | 2                | 2                | 6                                     |
| 1             | 1           | 3                | 3                | 4                                     |
| 2             | 2           | 0                | 0                | 3                                     |
| 2             | 2           | 1                | 1                | 1                                     |
| 2             | 2           | 2                | 2                | 0                                     |
| 2             | 2           | 3                | 3                | 5                                     |
| 3             | 3           | 0                | 0                | 5                                     |
| 3             | 3           | 1                | 1                | 3                                     |
| 3             | 3           | 2                | 2                | 2                                     |
| 3             | 3           | 3                | 3                | 0                                     |


*/
