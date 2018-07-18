@SuppressWarnings({"rawtypes", "unchecked"})
public class SeperateChainHashST<Key, Val> implements IAssociativeArray<Key, Val>{
    private static final int INIT_CAPACITY = 4;

    private int N;
    private int m;
    private SequentialSearchST<Key, Val>[] st;

    public SeperateChainHashST(){
        this(INIT_CAPACITY);
    }

    public SeperateChainHashST(int m){
        this.m = m;
        st = (SequentialSearchST<Key, Val>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++){
            st[i] = new SequentialSearchST<Key, Val>();
        }
    }

    private void resize(int chains){

    }

    private int hash(Key key){
        //bitwise AND to cancel first bit. avoid overflow
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public Val get(Key key){
        if(key == null) throw new IllegalArgumentException("Key is null");
        int i = hash(key);
        return st[i].get(key);
    }

    public void put(Key key, Val val){
        if(key == null) throw new IllegalArgumentException("Key is null");
        if(val == null){
            delete(key);
            return;
        }

        if (N >= 10*m) resize(2*m);

        int i = hash(key);
        if(!st[i].contains(key)) N++;
        st[i].put(key,val);

    }

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("Key is null");

        int i = hash(key);
        if(st[i].contains(key)) N--;
        st[i].delete(key);

        if(m > INIT_CAPACITY && N <= 2*m) resize(m/2);
    }


}
