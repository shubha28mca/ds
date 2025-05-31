import java.util.*;

/**
 * Demonstrates Dijkstra's algorithm using an adjacency list with Edge class.
 * Suitable for graphs with non-negative weights.
 */
public class DijkstraShortestPath {

    // Edge definition as given
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Graph as adjacency list
    private Map<Integer, List<Edge>> adjList = new HashMap<>();

    // Add edge to the graph (undirected)
    public void addEdge(int from, int to, int weight) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.putIfAbsent(to, new ArrayList<>());
        adjList.get(from).add(new Edge(to, weight));
        adjList.get(to).add(new Edge(from, weight)); // comment if directed
    }

    /**
     * Dijkstra's algorithm to compute shortest paths from `source` node.
     * @param source starting node
     * @return map of node to shortest distance from source
     */
    public Map<Integer, Integer> dijkstra(int source) {
        Map<Integer, Integer> dist = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for(int node: adjList.keySet())
        {
            dist.putIfAbsent(node, Integer.MAX_VALUE);
        }
                

        pq.offer(new int[]{source, 0});
        dist.put(source, 0);

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int currentDist = curr[1];

            // If a better distance was already found, skip
            if (currentDist > dist.getOrDefault(node, Integer.MAX_VALUE)) {
                continue;
            }

            for (Edge edge : adjList.getOrDefault(node, Collections.emptyList())) {
                int neighbor = edge.to;
                int newDist = currentDist + edge.weight;

                if (newDist < dist.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    dist.put(neighbor, newDist);
                    pq.offer(new int[]{neighbor, newDist});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        DijkstraShortestPath graph = new DijkstraShortestPath();

        // Sample graph construction
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);

        int source = 0;
        Map<Integer, Integer> shortestPaths = graph.dijkstra(source);

        System.out.println("Shortest paths from node " + source + ":");
        for (Map.Entry<Integer, Integer> entry : shortestPaths.entrySet()) {
            System.out.println("To node " + entry.getKey() + " -> distance = " + entry.getValue());
        }
    }
}

/*
Time: O((V + E) * log V)
*/

