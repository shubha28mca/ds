# ds
# Java
## Table of Contents
1. [List or stack](#list)
2. [Set](#set)
3. [Queue](#queue)
4. [Heap](#heap)
5. [Deque](#deque-double-ended-queue)
6. [Map](#map)
7. [Graph](#graph)
8. [Iterator](#iterator)
9. [Trie](#trie)
10. [Common methods](#common-methods)
     

 
11. [Common Exceptions](#common-exceptions)
12. [Conversions](#conversions)
13. [Concurrency](#concurrency)
14. [Backtracking](#Backtracking)

## Implementation of datastructure
````
Note: to use the below collection we need to use the below import
import java.util.*;
````

### List: 
ArrayList (dynamic resizing array)  
LinkedList ( implementation of doubly linked list)  
Vector ( dynamic size array) ( legacy class) thread safe class ( Not to using this in a single threaded environment)  
Stack (legacy class, inheri from vector class) thread safe class ( Not to using this in a single threaded environment)  

#### Implementation
```java
List <data-type> list1= new ArrayList();  
List <data-type> list2 = new LinkedList();  
List <data-type> list3 = new Vector();  
List <data-type> list4 = new Stack();
```

```java

itr=list.iterator();  
while(itr.hasNext()){  
System.out.println(itr.next());  
}  
```

```java
//Stack (LIFO)
Stack<String> s=new Stack<String>();  
s.add("Raj");
s.push("Temp"); // both push and add work fine

System.out.println("Removed:"+ s.pop());
System.out.println("Front:"+ s.peek());
Iterator itr=s.iterator();  
while(itr.hasNext()){  
		System.out.println(itr.next());  
}

// Pop elements from the stack
while(!s.isEmpty()) {
    System.out.println(s.pop());
}
```  

### Set:
HashSet ( quickly inset delete, search the keys in a set, using hashing to store the keys) no ordering of the data.  
Set<String> ts = new HashSet<>();  
TreeSet ( stores the keys and use self balancing binary search tree for string the keys) (Stored and Fetched in Shorted Order)  
  
	
```java
	Set<String> ts = new TreeSet<>();
	// Adding elements in above object
	// using add() method
	ts.add("ram");
	ts.add("Ram");
	ts.add("madan");
	ts.add("A");
	ts.add("B");
	ts.add("Z");
	// Now we will be using for each loop in order
	// to iterate through the TreeSet
	for (String value : ts)System.out.print(value + ", ");
	
	// Output: A, B, Ram, Z, madan, ram,
```	
	
Note: for reverse order we can use below code or put into a Stack OR use NavigableSet
	
```java	
NavigableSet<Integer> treereverse = ts.descendingSet();
            // getting iterated view of NavigableSet
            Iterator<Integer> iterator = treereverse.iterator();
LinkedHashSet (store the keys in the insertion order) same as hash set but the data are in order.  
	
Set<String> ts = new LinkedHashSet<>();
```	
### Queue
#### LinkedList (queue operation in the linked list)

```java
Queue<String> q=new LinkedList<String>(); (FIFO)   
        q.add("Amit Sharma");  
        q.add("Vijay Raj");  
        q.add("JaiShankar");  
        q.add("Raj");
        System.out.println("Removed:"+ q.remove());
        System.out.println("Front:"+ q.peek());
        Iterator itr=q.iterator();  
        while(itr.hasNext()){  
            System.out.println(itr.next());  
        }
```
	
#### ArrayDeque ( Array implementation of the queue)   

### Heap:
#### PrioriyQueue (Heap) queues with priority   
	```
	peek()	Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
        poll()	Retrieves and removes the head of this queue, or returns null if this queue is empty.
	```
##### Max Heap (Max element in the root)  

```
PriorityQueue<Integer> pQueue  
            = new PriorityQueue<Integer>(
                Collections.reverseOrder());
                
        pQueue.add(10);
        pQueue.add(50);
        pQueue.add(30);
        pQueue.add(40);
        pQueue.add(400);
        System.out.println("Head value using peek function:"
                           + pQueue.peek()); //400
        
        // Printing all elements
        System.out.println("The queue elements:");
        Iterator itr = pQueue.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());
        pQueue.poll(); // Removing the top priority element (or head)
        boolean b = pQueue.contains(20);
        Object[] arr = pQueue.toArray();
```
	
##### Min heap ( min element in the root)
```java	
PriorityQueue<Integer> pQueue
            = new PriorityQueue<Integer>(); // all other implementation is same as MaxHeap
```	
Note: for Thread safe we can use PriorityBlockingQueue		       				
	```java
	Queue<Integer> pbq
         = new PriorityBlockingQueue<Integer>();
	```

### Deque: (Double ended Queue)
insert and delete items in both the ends
Using LinkedList  
	
```java
Deque<String> deque
            = new LinkedList<String>();
        deque.add("Element 1 (Tail)");
        deque.addFirst("Element 2 (Head)");
        deque.addLast("Element 3 (Tail)");
        // Add at the first
        deque.push("Element 4 (Head)");
for (Iterator itr = deque.iterator();
             itr.hasNext();) {
            System.out.print(itr.next() + " ");
        }

        deque.removeFirst();
        deque.removeLast();
	
Output: Element 4 (Head) Element 2 (Head) Element 1 (Tail) Element 3 (Tail)
```	
To print in Decending order

```java
for (Iterator itr = deque.descendingIterator();
             itr.hasNext();) {
            System.out.print(itr.next() + " ");
        }
	
Output: Element 3 (Tail) Element 1 (Tail) Element 2 (Head) Element 4 (Head)
```

ArrayDeque (Stack can be implemented by this)   
	
### Map:
Collection of key value pairs
#### HashMap search O(1)
#### Implementation
Direct hashMap

```java
HashMap<Integer, String> hm = new HashMap<Integer, String>(); 
hm.put(110,"Ravi"); 
if(hm.containsKey(110)) sout(“Key Exist”);
Iterator <Integer> it = hm.keySet().iterator();
while(it.hasNext())  
{  
	int key=(int)it.next();  
	System.out.println("Roll no.: "+key+"     name: "+hm.get(key));  
}
```
	
Using Map Interface  

```java
Map<String,String> map = new HashMap<String,String>(); 
map.put("Gujarat", "Gandhi Nagar");
for (String State : map.keySet())  
	//using keyset() method for iteration over keySet  
	System.out.println("State: " + State);   
for (String Capital : map.values())         //using values() for iteration over keys  
	System.out.println("Capiatl: " + Capital); 
//OR
for (Map.Entry<String,Float> entry : map.entrySet()) //using map.entrySet() for iteration  
{  
	//returns keys and values respectively  
	System.out.println("Item: " + entry.getKey() + ", Price: " + entry.getValue());   
}   
```
#### TreeMap (self balancing binary search tree) Red black tree, get the tems in the sorted order. Search insert delete operation are higher compare to HashMap because it does additional operation to arrange the data. Search (O(logn)) ***(Stored and Fetched in Shorted Order)***
#### LinkedHashMap, (store the items according to their insertion order)
#### HashTable just like HashMap has thread safty   
```java	
Hashtable<Integer,String> hm=new Hashtable<Integer,String>();    hm.put(100,"Amit");  
 for(Map.Entry m:hm.entrySet()){  
   System.out.println(m.getKey()+" "+m.getValue());  
  } 
```
### Graph
1. Directional
2. Undirectional  
```java	
import java.util.*;
 
class Graph<T> {
 
    // We use Hashmap to store the edges in the graph
    private Map<T, List<T> > map = new HashMap<>();
 
    // This function adds a new vertex to the graph
    public void addVertex(T s)
    {
        map.put(s, new LinkedList<T>());
    }
 
    // This function adds the edge
    // between source to destination
    public void addEdge(T source,
                        T destination,
                        boolean bidirectional)
    {
 
        if (!map.containsKey(source))
            addVertex(source);
 
        if (!map.containsKey(destination))
            addVertex(destination);
 
        map.get(source).add(destination);
        if (bidirectional == true) {
            map.get(destination).add(source);
        }
    }
 
    // This function gives the count of vertices
    public void getVertexCount()
    {
        System.out.println("The graph has "
                           + map.keySet().size()
                           + " vertex");
    }
 
    // This function gives the count of edges
    public void getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection == true) {
            count = count / 2;
        }
        System.out.println("The graph has "
                           + count
                           + " edges.");
    }
 
    // This function gives whether
    // a vertex is present or not.
    public void hasVertex(T s)
    {
        if (map.containsKey(s)) {
            System.out.println("The graph contains "
                               + s + " as a vertex.");
        }
        else {
            System.out.println("The graph does not contain "
                               + s + " as a vertex.");
        }
    }
 
    // This function gives whether an edge is present or not.
    public void hasEdge(T s, T d)
    {
        if (map.get(s).contains(d)) {
            System.out.println("The graph has an edge between "
                               + s + " and " + d + ".");
        }
        else {
            System.out.println("The graph has no edge between "
                               + s + " and " + d + ".");
        }
    }
    
    // get the adjecent 
    public List<T> getEdges(T s)
    {
        return map.get(s);
    }
 
    // Prints the adjancency list of each vertex.
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
 
        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
 
        return (builder.toString());
    }
    
    public void BFS(T start)
    {
        Set<T> visited = new HashSet<>(); // we can use LinkedHashSet, 
        // which will contains the added Nodes in a order
        Queue<T> q = new LinkedList<>();
        q.add(start);
        visited.add(start);
        while(!q.isEmpty())
        {
            T temp = q.remove();
            System.out.println("Visit"+temp.toString());
            for(T vertex: getEdges(temp))
            {
                if(!visited.contains(vertex))
                {
                    visited.add(vertex);
                    q.add(vertex);
                }
            }
        }
    }
    
    public void DFS(T start)
    {
        Set<T> visited = new HashSet<>(); // we can use LinkedHashSet, 
        // which will contains the added Nodes in a order
        Stack<T> s = new Stack<>();
        s.add(start);
        visited.add(start);
        while(!s.isEmpty())
        {
            T temp = s.pop();
            
            /*if(!visited.contains(temp))
            {
                visited.add(temp);
                System.out.println("Visit"+temp.toString());
                for(T vertex: getEdges(temp))
                {
                    s.push(vertex);
                }
            }*/
            System.out.println("Visit"+temp.toString());
            for(T vertex: getEdges(temp))
            {
                if(!visited.contains(vertex))
                {
                    visited.add(vertex);
                    s.add(vertex);
                }
            }
        }
    }
}

public class HelloWorld{

     public static void main(String []args){
        // Object of graph is created.
        Graph<Integer> g = new Graph<Integer>();
 
        // edges are added.
        // Since the graph is bidirectional,
        // so boolean bidirectional is passed as true.
        g.addEdge(0, 1, true);
        g.addEdge(0, 4, true);
        g.addEdge(1, 2, true);
        g.addEdge(1, 3, true);
        g.addEdge(1, 4, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 4, true);
        g.addEdge(2, 5, true);
 
        // Printing the graph
        System.out.println("Graph:\n"
                           + g.toString());
 
        // Gives the no of vertices in the graph.
        g.getVertexCount();
 
        // Gives the no of edges in the graph.
        g.getEdgesCount(true);
 
        // Tells whether the edge is present or not.
        g.hasEdge(3, 4);
        
        // get Adjcent Vertex
        List<Integer> adjs = g.getEdges(3);
        
        for(Integer val: adjs)
        {
            System.out.println(val);
        }
        System.out.println("Print BFS start from 3");
        g.BFS(3);
        System.out.println("Print DFS start from 3");
        g.DFS(3);
 
        // Tells whether vertex is present or not
        g.hasVertex(5);
     }
}
``` 
	
3. Weighted graph
	
### Iterator:  
to Iterator the element from a collection   
It is an interface to implement walkthrow for the collection  
1. hasNext()
2. next()
3. remove()  
	
```java	
		Iterator itr=<Collection-Object>.iterator();
		while(itr.hasNext()){  
            System.out.println(itr.next());  
        }
```
	
### Trie:
This Data structure is just like nary tree and usualy used to store the disctionary

#### Structure:
```private class TriNode {
        Map<Character, TriNode> children;
        boolean endOfWord;
        public TriNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }
```
#### Creation of Root object:
	```private final TriNode root;
	```
#### Insertions:
	
```public void insert(String s)
    {
        TriNode current = root;
        for(char ch : s.toCharArray())
        {
            TriNode node = current.children.get(ch);
            if(node == null)
            {
                node = new TriNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }
```
	
#### Search:
	
```public boolean search(String word)
    {
        TriNode current = root;
        for(char ch: word.toCharArray())
        {
            TriNode node = current.children.get(ch);
            if(node == null)
            {
                return false;
            }
            current = node;
        }

        return current.endOfWord;
    }
```	
	
#### Deletion:
	
```public void delete(String word)
    {
        delete(root, word, 0);
    }


    public boolean delete(TriNode current, String word, int index)
    {
        if ( index == word.length())
        {
            // when end of the word reached only delete
            if (!current.endOfWord)
            {
                return false;
            }
            current.endOfWord = false;
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TriNode node = current.children.get(ch);
        if(node == null)
        {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index+1);

        if(shouldDeleteCurrentNode)
        {
            current.children.remove(ch);
            return current.children.size() == 0;
        }
        return false;
    }
```
	
	
### Common methods:
1. add()
2. addAll()
3. remove()
4. removeAll()
5. size()
6. clear()
7. contains()
8. containsAll()
9. retain()
10. retailAll()
11. toArray()
12. isEmpty()  

### Common Exceptions:
1. NullPointerException
2. ClasscaseException
3. IllegalArgumentException
4. IlligalStateException
5. UnSupportedOperationException

### Conversions:
#### Primitive array to List
	
```java	
String domains[] = {"Practice", "More",
                             "Code", "Quiz"};
        // Here we are making a list named as Collist
        List colList =
            new ArrayList(Arrays.asList(domains));
```
	
#### Hashset to array
	
```java	
Set<String> s = new HashSet<String>();
String arr[] = new String[s.size()];
arr = s.toArray(arr);
String to charArray
String s = "MyNameisCoder";
char[] gfg = s.toCharArray();
```
#### Chararray to string
```java	
char[] a ={‘a’,’b’}
String string = String.valueOf(a);
```	
	
#### sample input reader	




### Algorithms
#### Collections class ( implementation of basic operations)
1. binarySearch() 
2. sort() // this only works for ArrayList and LinkedList
**Implementation**  
1. Premitive

```java
ArrayList<String> al = new ArrayList<String>();
        al.add("i am a coder");
        al.add("Friends");
Collections.sort(al); // sort assending
Collections.sort(al, Collections.reverseOrder()); // sort in reverse order
```
	
2. Object  

```java	
ArrayList<Student> ar = new ArrayList<Student>();
        ar.add(new Student(111, "bbbb", "london"));
ar.add(new Student(131, "aaaa", "nyc"));
Collections.sort(ar, new Sortbyroll()); // sort the array

// we need to define the class as
class Sortbyroll implements Comparator<Student>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Student a, Student b)
    {
        return a.rollno - b.rollno;
    }
}
```	
Note: so to work with Map and any other DS we need to convert them to Array list or Linked List

```java	
		ArrayList<String> sortedKeys
            = new ArrayList<String>(map.keySet());
 
        Collections.sort(sortedKeys); // now we can get the keys from the arry list and fetch the 
```
 

3. max()
4. min()
5. reverse()
6. fill()



### Concurrency
processing of data structure  
Thead safe datastructures  
AtomicInteger  
ConcurrentHashMap  
```java	
ConcurrentHashMap<String, Integer>  mymap = new ConcurrentHashMap<String,  Integer>();
mymap.put("AAA", 10); 
mymap.containsValue(255)
```	
#### Hasalcast distributed datastructure	
	
#### Thread library
Implementing Runnable method  

```java	
public class Main implements Runnable {
  public static void main(String[] args) {
    Main obj = new Main();
    Thread thread = new Thread(obj);
    thread.start();
    System.out.println("This code is outside of the thread");
  }
  public void run() {
    System.out.println("This code is running in a thread");
  }
}
```
Creating thread class  

```java
 public class Main extends Thread {
  public static void main(String[] args) {
    Main thread = new Main();
    thread.start();
    System.out.println("This code is outside of the thread");
  }
  public void run() {
    System.out.println("This code is running in a thread");
  }
}
``` 
// below is an example of thread  

```java	
class Count
{
    int count; // we can is AtomicInteger
    
    public void increment() // we can add the syncronise in this method to create synconise method
    {
        count++;
    }
}
 
	Count c= new Count();
        Thread t1 = new Thread(new Runnable(){
            public void run()
            {
                for( int i=0;i<1000;i++)
                c.increment();   
            }
        });
        
        Thread t2 = new Thread(new Runnable(){
            public void run()
            {
                for( int i=0;i<1000;i++)
                c.increment();    
            }
        });
        
        try{
        t1.start();
        t2.start();
        
        t1.join();
        t1.join();
        }
        catch(Exception ex){}
        System.out.println("Count"+c.count);
 ```

#### Thread pool Executor framework

#### Backtracking
```
public String swap(String a, int i, int j) {
  char temp;
  char[] charArray = a.toCharArray();
  temp = charArray[i];
  charArray[i] = charArray[j];
  charArray[j] = temp;
  return String.valueOf(charArray);
}
private void backtrack(String s, int idx, int N) {
  if (idx == N)
    System.out.println(s);
  else {
    for (int i = idx; i <= N; i++) {
      s = swap(s, idx, i);
      backtrack(s, idx + 1, N);
      s = swap(s, idx, i);
    }
  }
}
solve() {
  String s = ”ABC”;
  int N = s.length;
  backtrack(s, 0, N - 1);
}
```
#### Backtracking
