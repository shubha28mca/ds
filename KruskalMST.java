import java.util.*;

/**
 * Kruskal's MST Algorithm using adjacency list.
 *
 * ✅ Approach:
 * 1. Convert adjList to flat edge list.
 * 2. Sort edges by weight.
 * 3. Use Union-Find to avoid cycles.
 * 4. Add edge if it connects two different components.
 *
 * ⏱ Time Complexity: O(E log E)
 * 🧠 Space Complexity: O(V + E)
 */
public class KruskalMST {

    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private Map<Integer, List<Edge>> adjList = new HashMap<>();

    // Add undirected edge
    public void addEdge(int from, int to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(from, to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(to, from, weight));
    }

    // Union-Find DSU structure
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) return false;
            if (rank[xr] < rank[yr]) parent[xr] = yr;
            else if (rank[xr] > rank[yr]) parent[yr] = xr;
            else {
                parent[yr] = xr;
                rank[xr]++;
            }
            return true;
        }
    }

    public void kruskalMST() {
        List<Edge> allEdges = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        // Convert adjList to unique edge list
        for (int u : adjList.keySet()) {
            for (Edge e : adjList.get(u)) {
                String edgeKey = Math.min(e.from, e.to) + "-" + Math.max(e.from, e.to);
                if (!seen.contains(edgeKey)) {
                    allEdges.add(e);
                    seen.add(edgeKey);
                }
            }
        }

        // Sort edges by weight
        allEdges.sort(Comparator.comparingInt(e -> e.weight));

        int n = adjList.size();
        DSU dsu = new DSU(n);
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge e : allEdges) {
            if (dsu.union(e.from, e.to)) {
                mst.add(e);
                totalWeight += e.weight;
            }
        }

        // Output MST
        System.out.println("Edges in MST:");
        for (Edge e : mst) {
            System.out.println(e.from + " -- " + e.to + " == " + e.weight);
        }
        System.out.println("Total Weight of MST: " + totalWeight);
    }

    // Main method with example
    public static void main(String[] args) {
        KruskalMST graph = new KruskalMST();

        // Example graph with 5 nodes (0 to 4)
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        graph.kruskalMST();
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
output:
Edges in MST:
0 -- 1 == 2
1 -- 2 == 3
1 -- 4 == 5
0 -- 3 == 6
Total Weight of MST: 16

Output Explanation
Total nodes = 5 → MST should have exactly 4 edges (V - 1)
Algorithm chooses the lightest non-cyclic edge at each step
Avoids cycles using Union-Find
Ensures all nodes are connected with minimum total weight

*/
