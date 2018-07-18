public interface IQueue<E>{

    public void enqueue(E item);
    public E dequeue();
    public boolean isEmpty();
    public int size();

}
