public interface IPriorityQueue<E>{

    public void add(E item, int priority);
    public E remove();
    public E peek();
    public boolean isEmpty();
    public int size();

}
