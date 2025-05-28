// Step 1: Calculate in-degree for each node (number of incoming edges).
// Step 2: Initialize a queue and enqueue all nodes with in-degree equal to 0 (no dependencies).
// Step 3: While the queue is not empty:
//    - Dequeue a node and add it to the result list.
//    - For each child (dependent node) of the current node:
//        - Decrease its in-degree by 1.
//        - If its in-degree becomes 0, enqueue it.
// Step 4: After processing all nodes, check if the result list contains all nodes.
//    - If yes, return the result (valid topological order).
//    - If not, the graph contains a cycle and topological sorting is not possible.
import java.util.*;

class Edge {
    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class KahnsTopologicalSortWithWeights {

    private Map<Integer, List<Edge>> adjList = new HashMap<>();

    public List<Integer> topologicalSort() {
        // Step 1: Compute in-degrees
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int node : adjList.keySet()) {
            inDegree.putIfAbsent(node, 0);
            for (Edge edge : adjList.get(node)) {
                inDegree.put(edge.to, inDegree.getOrDefault(edge.to, 0) + 1);
            }
        }

        // Step 2: Add nodes with in-degree 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();

        // Step 3: Kahn's BFS process
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);

            if (adjList.containsKey(node)) {
                // Get all the nodes which are dependent of the current node
                for (Edge edge : adjList.get(node)) { // we can also implement this in a List which will help to get the dependendent nodes without vaults
                    int neighbor = edge.to;
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Step 4: Check for cycles
        if (topoOrder.size() != inDegree.size()) {
            throw new IllegalStateException("Graph contains a cycle, topological sort not possible.");
        }

        return topoOrder;
    }

    public void addEdge(int from, int to, int weight) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.get(from).add(new Edge(to, weight));
        // Ensure all nodes are in the map
        adjList.putIfAbsent(to, new ArrayList<>());
    }

    public static void main(String[] args) {
        KahnsTopologicalSortWithWeights graph = new KahnsTopologicalSortWithWeights();

        // Sample DAG
        graph.addEdge(5, 2, 1);
        graph.addEdge(5, 0, 1);
        graph.addEdge(4, 0, 1);
        graph.addEdge(4, 1, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);

        try {
            List<Integer> sorted = graph.topologicalSort();
            System.out.println("Topological Order: " + sorted);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
