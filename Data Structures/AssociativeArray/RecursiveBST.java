@SuppressWarnings({"rawtypes", "unchecked"})
public class RecursiveBST<Key extends Comparable, Val> implements IAssociativeArray<Key,Val>{
    private Node root;          // root of BST

    private class Node {
        private Key key;              // sorted by key
        private Val val;              // associated data
        private Node left, right;     // left and right subtrees
        private int size;

        /**
         * Dummy field so some of Meng's excercises below compile
         **/
        private int data;

        public Node(Key key, Val val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            if (key.getClass() == Integer.class){
               data = (Integer)key;
            }
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }
    }

    public RecursiveBST(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public int size(){
        return size(root);
    }

    public int size(Node n){
        if(n == null) return 0;
        return n.size;
    }

    public Val get(Key key){
        return get(root, key);
    }

    public Val get(Node n, Key key){
        if (n == null) return null;
        int comp = key.compareTo(n.key);
        if(comp < 0) return get(n.left, key);
        else if (comp > 0) return get(n.right,key);
        else return n.val;
    }

    public void put(Key key, Val val){
        root = put(root, key, val);
    }

    public Node put(Node n, Key key, Val val){
        if(n == null) return new Node(key, val, 1);
        int comp = key.compareTo(n.key);
        if (comp < 0) n.left = put(n.left, key, val);
        else if (comp > 0) n.right = put(n.right, key, val);
        else n.val = val;
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node deleteMin(Node n){
        if (n.left == null) return n.right;
        n.left = deleteMin(n.left);
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    public void delete(Key key){
        root = delete(root, key);
    }

    public Node delete(Node n, Key key){
        if (n == null) return null;
        int comp = key.compareTo(n.key);
        if (comp < 0) n.left = delete(n.left, key);
        else if (comp > 0) n.right = delete(n.right, key);
        else {
            if(n.right == null) return n.left;
            if(n.left == null) return n.right;
            Node t = n;
            n = min(t.right);
            n.right = deleteMin(t.right);
            n.left = t.left;
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }



    /*
     * Some fun with recursive functions
     */

    public int countLeaves(){
        return countLeaves(root);
    }

    private int countLeaves(Node n){
        if(n == null) return 0;
        if(n.isLeaf()) return 1;
        return countLeaves(n.left) + countLeaves(n.right);
    }

    public int getHeight(){
        return getHeight(root);
    }

    private int getHeight(Node n){
        if(n == null) return 0;
        return 1 + Math.max(getHeight(n.left), getHeight(n.right));
    }

    // the following only works with integer data, the dummy field "data" was added so this compiles

    public int bound(int value){
        return bound(value, root, -1);
    }

    private int bound(int value, Node p, int working){
        if(p == null) return working;

        if(p.data >= value) return bound(value, p.left, working); //we only care about smaller values, so check left
        else /*if(p.data < value)*/{ // if statement ommited for compiler, but is logically equivalent
             working = p.data; // this node holds a valid entry, so update our working bound
             return bound(value,p.right,working); // only path to check is with bigger values AKA right
        }
    }

    public boolean isBST(){
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node p, int min, int max){
        if(p == null){
            return true;
        }
        if(p.data < min || p.data > max){
            return false;
        }

        return isBST(p.left, min, p.data - 1) && isBST(p.right, p.data + 1, max);
    }


    public boolean isBST2(){
        return isBST2(root);
    }

    private boolean isBST2(Node p){
        if(p == null){
            return true;
        }
        if(p.left != null && p.data < maxValue(p.left)){
            return false;
        }
        if(p.right != null && p.data > minValue(p.right)){
            return false;
        }

        return isBST2(p.left) && isBST2(p.right);
    }


    private int maxValue(Node n){
        if(n.right == null) return n.data;
        return maxValue(n.right);
    }

    private int minValue(Node n){
        if(n.left == null) return n.data;
        return minValue(n.left);
    }

    public boolean hasPathSum(int n){
        return hasPathSum(root, n);
    }

    private boolean hasPathSum(Node p, int n){
        if(p == null) return false;

        if(p.isLeaf()){
            return (p.data == n);
        }

        return hasPathSum(p.left, n - p.data) || hasPathSum(p.right, n - p.data);
    }

    //pre: a < b
    public int countKey(int a, int b){
        return countKey(a, b, root);
    }

    private int countKey(int a, int b, Node n){
        if(n == null) return 0;
        else if(n.data >= a && n.data <= b) return 1 + countKey(a, b, n.left) + countKey(a,b,n.right);
        else if(n.data < a) return countKey(a,b,n.right); //only care about larger nodes, right subtree
        else /*if(n.data > b)*/ return countKey(a,b,n.left); //only care about smaller nodes, left subtree
    }
}
