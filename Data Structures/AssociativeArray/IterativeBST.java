@SuppressWarnings({"rawtypes", "unchecked"})
public class IterativeBST<Key extends Comparable, Val> implements IAssociativeArray<Key,Val>{

    private Node root;          // root of BST

    private class Node {
        private Key key;              // sorted by key
        private Val val;              // associated data
        private Node left, right;     // left and right subtrees

        public Node(Key key, Val val) {
            this.key = key;
            this.val = val;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }
    }


   /***************************************************************************
    *  Insert item into BST, nonrecursive version
    ***************************************************************************/
    public void put(Key key, Val val) {
        Node z = new Node(key, val);
        if (root == null) {
            root = z;
            return;
        }
        Node parent = null, x = root;
        while (x != null) {
            parent = x;
            int res = key.compareTo(x.key);
            if      (res < 0) x = x.left;
            else if (res > 0) x = x.right;
            else {
                x.val = val;
                return;
            }   // overwrite duplicate
        }
        int res = key.compareTo(parent.key);
        if (res < 0) parent.left  = z;
        else         parent.right = z;
    }


   /***************************************************************************
    *  Search BST for given key, nonrecursive version
    ***************************************************************************/
    public Val get(Key key) {
        Node x = root;
        while (x != null) {
            int res = key.compareTo(x.key);
            if      (res < 0) x = x.left;
            else if (res > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    // is the given key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    // return size of tree (linear time operation)
    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        return 1 + size(x.left) + size(x.right);
    }

    public void delete(Key key){
        //find node to delete and its parent
        Node parent = null, x = root;
        while (x != null) {
            Node temp = x;
            int res = key.compareTo(x.key);
            if      (res < 0) x = x.left;
            else if (res > 0) x = x.right;
            else break; // found
            parent = temp;
        }

        if(x == null) return; // element was not in the tree

        if(parent == null) { // trying to delete root
            if (x.isLeaf()){ // just set parent's link to null
                root = null;
            } else if (x.left != null && x.right == null){ //redirect around x from parent to single child
                root = x.left;
            } else if(x.left == null && x.right !=null){
                root = x.right;
            } else { // two children, complicated case. Implemented on left subtree
                //find greatest element in left subtree
                Node zparent = x, z = x.left;
                while(z.right !=null){
                    zparent = z;
                    z = z.right;
                }

                // swap that up to x
                x.key = z.key;
                x.val = z.val;

                //now handle deletion for z
                if(z.left == null){
                    zparent.right = null;
                } else {
                    zparent.right = z.left;
                }
            }
            return;
        }

        int res = key.compareTo(parent.key);
        if (x.isLeaf()){ // just set parent's link to null
            if(res > 0) parent.right = null;
            else parent.left = null;
        } else if (x.left != null && x.right == null){ //redirect around x from parent to single child
            if(res > 0) parent.right = x.left;
            else parent.left = x.left;
        } else if(x.left == null && x.right !=null){
            if(res > 0) parent.right = x.right;
            else parent.left = x.right;
        } else { // two children, complicated case. Implemented on left subtree
            //find greatest element in left subtree
            Node zparent = x, z = x.left;
            while(z.right !=null){
                zparent = z;
                z = z.right;
            }

            // swap that up to x
            x.key = z.key;
            x.val = z.val;

            //now handle deletion for z
            if(z.left == null){
                zparent.left = null;
            } else {
                zparent.left = z.left;
            }
        }
    }

}
