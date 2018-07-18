public class Node<E>{
    private E info;
    private Node<E> next;

    public Node(E i){
      info = i;
      next = null;
    }

    public E getInfo(){
        return info;
    }

    public Node<E> getNext(){
        return next;
    }

    public void setNext(Node<E> n){
        next = n;
    }

}
