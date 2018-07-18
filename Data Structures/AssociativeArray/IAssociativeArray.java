/**
 * ADT for Symbol Tables
 **/
public interface IAssociativeArray<K, V>{
    public void put(K key, V value);
    public void delete(K key);
    public V get(K key);
    public boolean contains(K key);
    public boolean isEmpty();
    public int size();

}
