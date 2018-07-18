public class Node<E>{
    private E info;
    private Node<E> next;

    public Node(E i, Node<E> n){
        info = i;
        next = n;
    }

    public E getInfo(){
        return info;
    }

    public Node<E> getNext(){
        return next;
    }

}
