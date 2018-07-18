public class Node<E> implements Comparable<Node<E>>{

    private int priority;
    private E data;
    private Node<E> right;
    private Node<E> left;
    private Node<E> parent;

    public Node(E d, int p){
        priority = p;
        data = d;
    }

    public E getInfo(){
        return data;
    }

    public int getPriority(){
        return priority;
    }

    public void setPriority(int p){
        priority = p;
    }

    public void setInfo(E d){
        data = d;
    }

    public void setLeft(Node<E> l){
        left = l;
    }

    public void setRight(Node<E> r){
        right = r;
    }

    public void setParent(Node<E> p){
        parent = p;
    }

    public Node<E> getParent(){
        return parent;
    }

    public Node<E> getRight(){
        return right;
    }

    public Node<E> getLeft(){
        return left;
    }

    public boolean isLeaf(){
        return right == null && left == null;
    }

    public int compareTo(Node<E> other){
        if(this.getPriority() == other.getPriority()){
            return 0;
        } else if(this.getPriority() > other.getPriority()){
            return 1;
        } else {
            return -1;
        }
    }
}
