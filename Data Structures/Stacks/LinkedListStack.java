public class LinkedListStack<E> implements IStack<E>{

    private int size;
    private Node<E> top;

    public LinkedListStack(){
        size = 0;
        top = null;
    }

    public void push(E item){
        top = new Node<E>(item, top);
        size++;
    }

    public E pop(){
        if(this.isEmpty()){
            System.out.println("Underflow");
            return null;
        } else {
            size--;
            E result = top.getInfo();
            top = top.getNext();
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
