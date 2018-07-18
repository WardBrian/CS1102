import java.util.*;
public class MaxIntPQ{

    private class Node {
        int info;
        Node parent;
        Node left;
        Node right;
        public Node(int i){
            info = i;
        }
    }

    private Node root;

    private int N; // the size of the heap

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    private Stack<Integer> getPath(int n){
        //this finds the position based on the node#
        Stack<Integer> Stack = new Stack<Integer>();
        while(n!=1){
            Stack.push(n%2);
            n = n/2;
        }
        return Stack;
    }

    public void insert(int x) {
        this.N++;
        Node toInsert = new Node(x);
        if(this.N == 1){
            this.root = toInsert;
        } else {
            Stack<Integer> Paths = getPath(this.N);
            Node pointer = this.root;
            while(!Paths.isEmpty()){
                int direction = Paths.pop();
                if(Paths.isEmpty() && direction == 0){
                    pointer.left = toInsert;
                    toInsert.parent = pointer;
                } else if(Paths.isEmpty() && direction == 1){
                    pointer.right = toInsert;
                    toInsert.parent = pointer;
                } else if(direction == 0){
                    pointer = pointer.left;
                } else {
                    pointer = pointer.right;
                }
            }
            this.swim(toInsert);
        }
  }

    private void swim(Node inserted) {
        Node pointer = inserted;
        while(pointer.parent!=null && this.N>0 && pointer.info > pointer.parent.info){
            int temp = pointer.info;
            pointer.info = pointer.parent.info;
            pointer.parent.info = temp;
            pointer = pointer.parent;
        }
    }


    // testing
    public void printLevelOrder(){
      printLevelOrder(root);
    }

    private void printLevelOrder(Node root){
        // Base Case
        if(root == null)
            return;

        // Create an empty queue for level order tarversal
        Queue<Node> q = new LinkedList<Node>();

        // Enqueue Root and initialize height
        q.add(root);
        while(true){
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.size();
            if(nodeCount == 0)
                break;
            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while(nodeCount > 0) {
                Node node = q.peek();
                System.out.print(node.info + " ");
                q.remove();
                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
                nodeCount--;
            }
                System.out.println();
        }
    }

    public static void main(String[] args) {
        MaxIntPQ q = new MaxIntPQ();

        for(int i = 0; i < 24; i++){
            int j = /*20 - i;//*/(int)(Math.random()*1000) - 500;
            q.insert(j);
        }

        System.out.println("Size is " + q.size());

       // q.printLevelOrder();
    }

}
