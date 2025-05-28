import java.util.*;

/**
 * Prim's Algorithm to find MST from adjacency list representation.
 * 
 * ✅ Time Complexity: O(E log V)
 * ✅ Space Complexity: O(V + E)
 */
public class PrimsMST {

    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private Map<Integer, List<Edge>> adjList = new HashMap<>();

    // Add undirected edge
    public void addEdge(int from, int to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, weight));
    }

    public void primMST() {
        int n = adjList.size();
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // [node, weight]
        int totalWeight = 0;

        // Start from node 0
        pq.offer(new int[] {0, 0});

        System.out.println("Edges in MST:");

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], wt = curr[1];

            if (visited[u]) continue;

            visited[u] = true;
            totalWeight += wt;

            if (wt != 0) {
                System.out.println("Included node: " + u + " with edge weight: " + wt);
            }

            for (Edge e : adjList.getOrDefault(u, new ArrayList<>())) {
                if (!visited[e.to]) {
                    pq.offer(new int[] {e.to, e.weight});
                }
            }
        }

        System.out.println("Total Weight of MST: " + totalWeight);
    }

    // Main method with example
    public static void main(String[] args) {
        PrimsMST graph = new PrimsMST();

        // Example graph with 5 nodes (0 to 4)
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        graph.primMST();
    }
}

/*
Input: 
   0
  / \
 2   6
/     \
1---3---4
|\  |  /
| \ | /
3  8 9
|   \
2----4
    7

Output:
Edges in MST:
Included node: 1 with edge weight: 2
Included node: 2 with edge weight: 3
Included node: 4 with edge weight: 5
Included node: 3 with edge weight: 6
Total Weight of MST: 16

Explanation
Starts at node 0

Picks the lightest edge at every step

Avoids visiting the same node again

Builds a tree with minimum total edge weight and all nodes included


*/
