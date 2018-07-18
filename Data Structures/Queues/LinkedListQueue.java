public class LinkedListQueue<E> implements IQueue<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListQueue(){
        size = 0;
        head = null;
        tail = null;
    }

    public void enqueue(E item){
        Node<E> node = new Node<E>(item);
        if(size == 0){
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    public E dequeue(){
        if(isEmpty()){
            throw new java.util.NoSuchElementException("Queue is empty");
        } else {
            size--;
            E result = head.getInfo();
            head = head.getNext();

            if(isEmpty()){
                tail = null;
            }

            return result;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
}
