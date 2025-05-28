import java.util.*;

public class DFSTopologicalSort {

    /**
     * Approach:
     * 1. Use DFS to visit each node.
     * 2. Maintain two sets:
     *    - visited: nodes that have been completely processed.
     *    - onStack: nodes currently in the recursion stack (to detect cycles).
     * 3. For each unvisited node, perform DFS:
     *    - Mark the node as visited and add to onStack.
     *    - Recur for all neighbors.
     *    - If a neighbor is already onStack, a cycle exists -> throw exception.
     * 4. After visiting all neighbors, remove node from onStack and push onto stack.
     * 5. After DFS finishes for all nodes, pop nodes from stack to get topological order.
     * 
     * Cycle detection ensures topological sort only on DAG.
     */
    public List<Integer> topologicalSort(Map<Integer, List<Integer>> adjList) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> onStack = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (Integer node : adjList.keySet()) {
            if (!visited.contains(node)) {
                if (dfs(node, adjList, visited, onStack, stack)) {
                    throw new IllegalStateException("Cycle detected; topological sort not possible.");
                }
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topoOrder.add(stack.pop());
        }

        return topoOrder;
    }

    /**
     * DFS helper method:
     * Returns true if a cycle is detected.
     */
    private boolean dfs(Integer node, Map<Integer, List<Integer>> adjList, Set<Integer> visited, Set<Integer> onStack, Stack<Integer> stack) {
        visited.add(node);
        onStack.add(node);

        for (Integer neighbor : adjList.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, adjList, visited, onStack, stack)) {
                    return true;  // cycle detected
                }
            } else if (onStack.contains(neighbor)) {
                return true; // cycle detected via back edge
            }
        }

        onStack.remove(node);
        stack.push(node);  // add node to stack after all descendants processed
        return false;
    }

    public static void main(String[] args) {
        DFSTopologicalSort topoSort = new DFSTopologicalSort();

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        adjList.put(5, Arrays.asList(2, 0));
        adjList.put(4, Arrays.asList(0, 1));
        adjList.put(2, Arrays.asList(3));
        adjList.put(3, Arrays.asList(1));

        try {
            List<Integer> order = topoSort.topologicalSort(adjList);
            System.out.println("Topological Order (DFS): " + order);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
