import java.util.*;

/**
 * Demonstrates Union-Find (Disjoint Set Union) using HashMap for String nodes.
 * This is useful when graph nodes are strings or arbitrary identifiers.
 */
public class UnionFindString {

    static class UnionFind {
        private Map<String, String> parent = new HashMap<>();
        private Map<String, Integer> rank = new HashMap<>();

        // Find with path compression
        public String find(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x); // initialize
                rank.put(x, 1);
            }

            if (!x.equals(parent.get(x))) {
                parent.put(x, find(parent.get(x))); // path compression
            }
            return parent.get(x);
        }

        // Union by rank
        public boolean union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);

            if (rootX.equals(rootY)) {
                return false; // x and y are already connected, would form a cycle
            }

            int rankX = rank.getOrDefault(rootX, 1);
            int rankY = rank.getOrDefault(rootY, 1);

            // attach smaller tree under root of larger tree
            if (rankX > rankY) {
                parent.put(rootY, rootX);
            } else if (rankX < rankY) {
                parent.put(rootX, rootY);
            } else {
                parent.put(rootY, rootX);
                rank.put(rootX, rankX + 1);
            }

            return true;
        }
        
        // Checks if two nodes are in the same connected component
        public boolean isConnected(String x, String y) {
            if (!parent.containsKey(x) || !parent.containsKey(y)) {
                return false;
            }
            return find(x).equals(find(y));
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind();

        // Sample graph with string node names
        String[][] edges = {
            {"Paris", "London"},
            {"London", "Berlin"},
            {"Berlin", "Rome"},
            {"Rome", "Paris"} // This edge creates a cycle
        };

        System.out.println("Checking for redundant connection in graph:");
        for (String[] edge : edges) {
            System.out.println("Processing edge: " + Arrays.toString(edge));
            if (!uf.union(edge[0], edge[1])) {
                System.out.println("Redundant edge found (creates cycle): " + Arrays.toString(edge));
            }
        }

        // Check connectivity
        System.out.println("\nConnectivity checks:");
        System.out.println("Paris connected to Rome? " + uf.isConnected("Paris", "Rome")); // true
        System.out.println("Paris connected to NYC? " + uf.isConnected("Paris", "NYC"));   // false
    }
}

/*
Checking for redundant connection in graph:
Processing edge: [Paris, London]
Processing edge: [London, Berlin]
Processing edge: [Berlin, Rome]
Processing edge: [Rome, Paris]
Redundant edge found (creates cycle): [Rome, Paris]

Connectivity checks:
Paris connected to Rome? true
Paris connected to NYC? false
*/
