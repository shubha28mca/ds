import java.util.*;

public class KahnsTopologicalSort {

    public static List<Integer> topologicalSort(int numVertices, List<List<Integer>> adjList) {
        Map<Integer, Integer> inDegree = new HashMap<>();

        // Step 1: Initialize in-degree with putIfAbsent
        for (int u = 0; u < numVertices; u++) {
            inDegree.putIfAbsent(u, 0); // ensure every node is in the map
            for (int v : adjList.get(u)) {
                inDegree.putIfAbsent(v, 0); // ensure target node exists in map
                inDegree.put(v, inDegree.get(v) + 1);
            }
        }

        // Step 2: Enqueue nodes with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();

        // Step 3: Kahn's Algorithm
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);

            for (int v : adjList.get(u)) {
                inDegree.put(v, inDegree.get(v) - 1);
                if (inDegree.get(v) == 0) {
                    queue.add(v);
                }
            }
        }

        // Step 4: Cycle detection
        if (topoOrder.size() != inDegree.size()) {
            throw new IllegalStateException("The graph contains a cycle; topological sort not possible.");
        }

        return topoOrder;
    }

    public static void main(String[] args) {
        int numVertices = 6;
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }

        // Directed edges (dependency direction: B -> A if A depends on B)
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
