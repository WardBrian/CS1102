public class SequentialSearchST<Key, Val> implements IAssociativeArray<Key, Val>{
    private int N;
    private Node first;

    private class Node{
        private Key key;
        private Val val;
        private Node next;

        public Node(Key key, Val val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST(){
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public Val get(Key key){
        for(Node x = first; x!=null; x=x.next){
            if(x.key.equals(key)){
                return x.val;
            }
        }
        return null;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public void put(Key key, Val val){
        for(Node x = first; x!=null; x=x.next){
            if(x.key.equals(key)){
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }

    public void delete(Key key){
        first = delete(first, key);
    }

    private Node delete(Node x, Key key){
        if(x == null) return null;
        if(key.equals(x.key)){
            N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }


    public Iterable<Key> keys(){
        java.util.Queue<Key> q = new java.util.LinkedList<Key>();
        for(Node x = first; x!=null; x=x.next){
            q.add(x.key);
        }
        return q;
    }
}
