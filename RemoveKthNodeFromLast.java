public class RemoveKthNodeFromLast {
    public static void main(String[] str)
    {
        Node head = new Node(1);
        Node head1 = new Node(2);
        Node head2 = new Node(3);
        Node head3 = new Node(4);
        Node head4 = new Node(5);
        Node head5 = new Node(6);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;

        traverse(head);
        head = removeKthLastNode(head, 1);
        traverse(head);

    }
    public static void traverse(Node head)
    {
        while(head!=null)
        {
            System.out.println(head.val);
            head= head.next;
        }
    }

    public static Node removeKthLastNode(Node head, int k) {
        // 1. Handle Edge Cases: Empty list or invalid k
        // If the list is null, or k is less than 1 (k-th last implies k >= 1)
        if (head == null || k <= 0) {
            return head; // Nothing to remove, or k is invalid. Return original head.
        }

        // Create a dummy node. This simplifies handling the edge case where
        // the head of the original list needs to be removed.
        Node dummy = new Node(0); // Placeholder value, assuming Node(int) constructor exists.
        dummy.next = head;

        // 'slow' pointer: Will eventually point to the node *before* the one to be removed.
        Node slow = dummy;

        // 'fast' pointer: Will move 'k' steps ahead of 'slow'.
        Node fast = dummy;

        // Phase 1: Move 'fast' pointer 'k' steps ahead.
        // We move k steps so that the distance between fast and slow is k.
        for (int i = 0; i < k; i++) {
            if (fast.next == null) {
                // This means k is greater than the number of nodes in the list.
                // The k-th last node does not exist.
                return head; // Return original head, as no removal should occur.
                // Or, throw new IllegalArgumentException("k is greater than list length");
            }
            fast = fast.next;
        }

        // Phase 2: Move both 'slow' and 'fast' pointers until 'fast' reaches the end.
        // When 'fast.next' is null, 'fast' is at the last node.
        // At this point, 'slow' will be exactly at the node *before* the k-th last node.
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Now, 'slow' points to the node immediately preceding the node to be removed.
        // The node to be removed is 'slow.next'.

        // Perform the removal: Skip the 'slow.next' node by linking 'slow' to 'slow.next.next'.
        // This single line correctly handles both cases:
        // 1. If 'slow' is the dummy node (meaning the original head is removed).
        // 2. If 'slow' is any other node in the list.
        slow.next = slow.next.next;

        // Return the new head of the list, which is pointed to by dummy.next.
        return dummy.next;
    }

    public static Node removeKthLastNode1(Node head, int k)
    {
        Node prevToRemove;
        prevToRemove = head;
        Node dummy = new Node(0);
        dummy.next = head;

        while(head!=null && k>=0)
        {
            k--;
            head= head.next;
        }
        while(head!=null)
        {
            prevToRemove = prevToRemove.next;
            head = head.next;
        }
        Node temp = prevToRemove.next;
        if(temp != null)
        {
            if(prevToRemove == dummy.next)
            {
                dummy.next = prevToRemove.next;
            }
            else
            {
                prevToRemove.next = prevToRemove.next.next;
            }
        }
        return dummy.next;
    }
}
