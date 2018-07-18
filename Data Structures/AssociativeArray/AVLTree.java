/**
* Self balancing BST.
**/
@SuppressWarnings({"rawtypes", "unchecked"})
public class AVLTree<Key extends Comparable, Val> implements IAssociativeArray<Key,Val>{

    private class Node{
        private final Key key;
        private Val val;
        private int size;
        private int height;
        private Node left;
        private Node right;

        public Node(Key k, Val v, int s, int h){
            key = k;
            val = v;
            size = s;
            height = h;
        }
    }

    private Node root;

    public AVLTree(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null) return 0;
        return x.size;
    }

    public int height(){
        return height(root);
    }

    private int height(Node x){
        if (x == null) return 0;
        return x.height;
    }

    public Val get(Key key){
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        Node x = get(root, key);
        if (x == null) return null;
        return x.val;
    }

    private Node get(Node x, Key key){
        if (x == null) return null;
        int comp = key.compareTo(x.key);
            if (comp < 0) return get(x.left, key);
        else if (comp > 0) return get(x.right, key);
        else return x;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public void put(Key key, Val val){
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if(val == null){
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Val val){
        if (x == null) return new Node(key, val, 1, 1);

        int comp = key.compareTo(x.key);
        if (comp < 0) x.left = put(x.left, key, val);
        else if (comp > 0) x.right = put(x.right, key, val);
        else {
            x.val = val;
            return x;
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return balance(x);
    }

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        root = delete(root, key);
    }

    private Node min(Node x){
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public Node delete(Node x, Key key){
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        if (comp < 0) x.left = delete(x.left, key);
        else if (comp > 0) x.right = delete(x.right, key);
        else {
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    private Node balance(Node x){
        if (balanceFactor(x) < -1){
            if(balanceFactor(x.right) > 0){
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) > 1){
            if(balanceFactor(x.left) < 0){
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }

    private int balanceFactor(Node x){
        return height(x.left) - height(x.right);
    }

    private Node rotateLeft(Node x){
        Node right = x.right;
        x.right = right.left;
        right.left = x;

        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        right.size = 1 + size(right.left) + size(right.right);
        right.height = 1 + Math.max(height(right.left), height(right.right));

        return right;
    }

    private Node rotateRight(Node x){
        Node left = x.left;
        x.left = left.right;
        left.right = x;

        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        left.size = 1 + size(left.left) + size(left.right);
        left.height = 1 + Math.max(height(left.left), height(left.right));

        return left;
    }

}
