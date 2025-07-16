//Return the node where two singly linked lists intersect If the linked lists don't intersect, return null.
public class GetIntersectionNode {

  public Node getIntersectionNodeoptimised(Node headA, Node headB) {
        // Handle edge cases where one or both lists are empty.
        if (headA == null || headB == null) {
            return null; // No intersection possible if either list is null.
        }

        // Initialize two pointers, one for each list.
        Node ptrA = headA;
        Node ptrB = headB;

        // Loop until the pointers meet.
        // They will meet at the intersection node or at null if there's no intersection.
        while (ptrA != ptrB) {
            // Move ptrA to the next node. If it reaches the end of list A,
            // redirect it to the head of list B.
            ptrA = (ptrA == null) ? headB : ptrA.next;

            // Move ptrB to the next node. If it reaches the end of list B,
            // redirect it to the head of list A.
            ptrB = (ptrB == null) ? headA : ptrB.next;
        }

        // At this point, ptrA (and ptrB) is either the intersection node or null.
        return ptrA;
    }
  

    /**
     * Finds the intersection node of two singly linked lists.
     *
     * This method uses a two-pointer approach to find the intersection.
     * It first calculates the lengths of both lists. Then, it moves the pointer
     * of the longer list forward by the difference in lengths, effectively
     * aligning both pointers to be equidistant from the end (or the intersection point).
     * Finally, it moves both pointers one step at a time until they meet,
     * which signifies the intersection node. If they reach the end without meeting,
     * it means there is no intersection.
     *
     * @param headA The head of the first linked list.
     * @param headB The head of the second linked list.
     * @return The intersection Node if found, otherwise null.
     */
    public Node getIntersectionNode(Node headA, Node headB) { // theere is an optimised version of this
        // Handle edge cases where one or both lists are empty.
        if (headA == null || headB == null) {
            return null; // No intersection possible if either list is null.
        }

        // Step 1: Calculate the length of list A.
        int lenA = getListLength(headA);

        // Step 2: Calculate the length of list B.
        int lenB = getListLength(headB);

        // Step 3: Determine the longer list and move its pointer forward
        // by the difference in lengths. This aligns both pointers to be
        // equidistant from the potential intersection point (or the end).
        Node ptrA = headA; // Pointer for list A
        Node ptrB = headB; // Pointer for list B

        // If list A is longer, move ptrA forward.
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                ptrA = ptrA.next;
            }
        }
        // If list B is longer, move ptrB forward.
        else if (lenB > lenA) {
            for (int i = 0; i < lenB - lenA; i++) {
                ptrB = ptrB.next;
            }
        }

        // Step 4: Move both pointers simultaneously until they meet or reach null.
        // If they meet, that's the intersection node.
        // If they both become null, there's no intersection.
        while (ptrA != null && ptrB != null) {
            if (ptrA == ptrB) {
                return ptrA; // Intersection found!
            }
            ptrA = ptrA.next;
            ptrB = ptrB.next;
        }

        // If the loop finishes, it means ptrA and ptrB became null without meeting,
        // indicating no intersection.
        return null;
    }

    /**
     * Helper method to calculate the length of a singly linked list.
     *
     * @param head The head of the linked list.
     * @return The number of nodes in the list.
     */
    private int getListLength(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    // --- Node class definition (for context and testing) ---
    // This Node class would typically be defined outside this Solution class
    // or as a static nested class if used only within this context.
    public static class Node {
        int val; // Value of the node
        Node next; // Pointer to the next node

        // Constructor to create a new node with a given value
        public Node(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "Node{" + "val=" + val + '}';
        }
    }

    // --- Example Usage (for testing) ---
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: Intersecting lists
        // List A: 4 -> 1 -> 8 -> 4 -> 5
        // List B: 5 -> 6 -> 1 -> 8 -> 4 -> 5 (intersects at Node 8)

        Node commonNode8 = new Node(8);
        Node commonNode4 = new Node(4);
        Node commonNode5 = new Node(5);

        commonNode8.next = commonNode4;
        commonNode4.next = commonNode5;

        Node headA1 = new Node(4);
        Node nodeA1 = new Node(1);
        headA1.next = nodeA1;
        nodeA1.next = commonNode8; // List A joins common part

        Node headB1 = new Node(5);
        Node nodeB1_6 = new Node(6);
        Node nodeB1_1 = new Node(1);
        headB1.next = nodeB1_6;
        nodeB1_6.next = commonNode8; // List B joins common part

        System.out.println("--- Example 1 (Intersecting) ---");
        Node intersection1 = sol.getIntersectionNode(headA1, headB1);
        if (intersection1 != null) {
            System.out.println("Intersection found at node with value: " + intersection1.val); // Expected: 8
        } else {
            System.out.println("No intersection found.");
        }

        // Example 2: Non-intersecting lists
        // List A: 1 -> 2 -> 3
        // List B: 4 -> 5 -> 6
        Node headA2 = new Node(1);
        headA2.next = new Node(2);
        headA2.next.next = new Node(3);

        Node headB2 = new Node(4);
        headB2.next = new Node(5);
        headB2.next.next = new Node(6);

        System.out.println("\n--- Example 2 (Non-Intersecting) ---");
        Node intersection2 = sol.getIntersectionNode(headA2, headB2);
        if (intersection2 != null) {
            System.out.println("Intersection found at node with value: " + intersection2.val);
        } else {
            System.out.println("No intersection found."); // Expected: No intersection found.
        }

        // Example 3: Intersection at head
        // List A: 1 -> 2
        // List B: 1 -> 2 (same list)
        Node headA3 = new Node(10);
        Node nodeA3_20 = new Node(20);
        headA3.next = nodeA3_20;

        Node headB3 = headA3; // Both lists start at the same node

        System.out.println("\n--- Example 3 (Intersection at Head) ---");
        Node intersection3 = sol.getIntersectionNode(headA3, headB3);
        if (intersection3 != null) {
            System.out.println("Intersection found at node with value: " + intersection3.val); // Expected: 10
        } else {
            System.out.println("No intersection found.");
        }

        // Example 4: One list is null
        System.out.println("\n--- Example 4 (One list is null) ---");
        Node intersection4 = sol.getIntersectionNode(headA1, null);
        if (intersection4 != null) {
            System.out.println("Intersection found at node with value: " + intersection4.val);
        } else {
            System.out.println("No intersection found."); // Expected: No intersection found.
        }
    }
}
