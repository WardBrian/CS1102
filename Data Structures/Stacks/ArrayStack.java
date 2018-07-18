public class ArrayStack<E> implements IStack<E>{

    private E[] storage;
    private int size;
    private static final int CAPACITY = 100;

    @SuppressWarnings("unchecked")
    public ArrayStack(){
        storage = (E[])new Object[CAPACITY];
        size = 0;
    }

    public void push(E item){
        if(size < CAPACITY){
            storage[size] = item;
            size++;
        }
    }

    public E pop(){
        E res = null;
        if(size > 0){
            size--;
            res = storage[size];
            storage[size] = null;
        }
        return res;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
