import java.util.*;

public class KahnsTopologicalSort {

    // Function to perform Kahn's Topological Sort
    public static List<Integer> topologicalSort(int numVertices, List<List<Integer>> adjList) {
        int[] inDegree = new int[numVertices];

        // Step 1: Compute in-degrees of all vertices
        for (int u = 0; u < numVertices; u++) {
            for (int v : adjList.get(u)) {
                inDegree[v]++;
            }
        }

        // Step 2: Add all vertices with in-degree 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();

        // Step 3: Process until queue is empty
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);

            // Decrease in-degree of all neighbors
            for (int v : adjList.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        // Step 4: Check if topological sort was possible (DAG check)
        if (topoOrder.size() != numVertices) {
            throw new IllegalStateException("The graph contains a cycle and topological sort is not possible.");
        }

        return topoOrder;
    }

    public static void main(String[] args) {
        int numVertices = 6;
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }

        // Example edges: directed graph
        /*
        ensure that the direction of the edges goes from a dependency to its dependent. For example, 
        if A depends on B (i.e., B must be completed before A), 
        then the graph should include an edge from B to A (B → A).
        here 2 has a dependency on 5.
        and 0 has a dependency on 5
        */
        adjList.get(5).add(2);
        adjList.get(5).add(0);
        adjList.get(4).add(0);
        adjList.get(4).add(1);
        adjList.get(2).add(3);
        adjList.get(3).add(1);

        try {
            List<Integer> result = topologicalSort(numVertices, adjList);
            System.out.println("Topological Order: " + result);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
/*
Output: Topological Order: [4, 5, 2, 3, 1, 0]
*/
