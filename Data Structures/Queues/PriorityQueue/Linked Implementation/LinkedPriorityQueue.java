/**
 * An implementation of a priority queue as a max heap. Uses a linked list and interative methods.
 **/
public class LinkedPriorityQueue<E> implements IPriorityQueue<E>{
    private Node<E> head;
    private int size;

    public void add(E item, int priority){
        Node<E> toInsert = new Node<E>(item, priority); // generate new Node
        size++; // update size first
        if(size == 1){ // this is the head
            head = toInsert;
            return;
        }
        Node<E> working = head; // working pointer to keep track of where we are
        int workingheight = getHeight(), workingsize = size; // height and size of current subtree
        while(true){
            double mid = (getMax(workingheight) + getMin(workingheight)) / 2.0;
            if (workingsize < mid) { // go left
                if(working.getLeft() == null){ // we're at the insert location
                    working.setLeft(toInsert);
                    toInsert.setParent(working);
                    break;
                } else { // we need to look deeper
                    working = working.getLeft();
                    // update size and height to now be just the left subtree
                    workingheight--;
                    workingsize = workingsize - (getMax(workingheight) / 2) - 1;
                    continue;
                }
            } else if (workingsize > mid){ // go right
                if(working.getRight() == null){ // we're at the insert location
                    working.setRight(toInsert);
                    toInsert.setParent(working);
                    break;
                } else { // we need to look deeper
                    working = working.getRight();
                    // update size and height to now be just the right subtree
                    workingsize = workingsize - (getMax(workingheight) / 2) - 1;
                    workingheight--;
                    continue;
                }
            }
        }

        working = toInsert; // move pointer to last inserted
        while(working != head && working.getPriority() > working.getParent().getPriority()){
            swap(working.getParent(), working); // move data around
            working = working.getParent(); // look up to see if it is now ordered
        }
    }

    public E peek(){
        if(size == 0){
            System.out.println("Heap Underflow");
            return null;
        }
        return head.getInfo();
    }

    public E remove(){
        if(size == 0){
            System.out.println("Heap Underflow");
            return null;
        }
        E res = head.getInfo(); // get info BEFORE modifying
        if(size == 1){ // destroy the heap entirely
            head = null;
            size--;
            return res;
        }
        Node<E> working = head; // working pointer to keep track of where we are
        int workingheight = getHeight(), workingsize = size; // height and size of current subtree
        while(true){
            double mid = (getMax(workingheight) + getMin(workingheight)) / 2.0;
            if (workingsize < mid) { // go left
                working = working.getLeft();
                if(working.isLeaf()){ // at the end
                    swap(head,working);
                    working.getParent().setLeft(null);
                    break;
                } else { // look deeper
                    // update size and height to now be just the left subtree
                    workingheight--;
                    workingsize = workingsize - (getMax(workingheight) / 2) - 1;
                    continue;
                }
            } else if (workingsize > mid){ // go right
                working = working.getRight();
                if(working.isLeaf()){ // at the end
                    swap(head,working);
                    working.getParent().setRight(null);
                    break;
                } else { // look deeper
                    // update size and height to now be just the right subtree
                    workingsize = workingsize - (getMax(workingheight) / 2) - 1;
                    workingheight--;
                    continue;
                }
            }
        }

        working = head; // now, start at top and look down
        while((working.getRight() != null && working.getPriority() < working.getRight().getPriority())
            || (working.getLeft() != null && working.getPriority() < working.getLeft().getPriority())){
            // check both branches exist, and swap with the highest existing one
            if(working.getLeft() != null && (working.getRight() == null  || working.getLeft().getPriority() > working.getRight().getPriority())){
                swap(working, working.getLeft()); // swap data
                working = working.getLeft(); // move pointer down tree to continue checking
            } else {
                swap(working, working.getRight()); // swap data
                working = working.getRight(); // move pointer down tree to continue checking
            }
        }

        size--; // update size last
        return res;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int getHeight(){
        return (int)Math.ceil(Math.log(size + 1) / Math.log(2));
    }

    private int getMax(int height){
        return (int)(Math.pow(2,height) - 1);
    }

    private int getMin(int height){
        return (int)(Math.pow(2,height- 1));
    }

    private void swap(Node<E> parent, Node<E> child){
        E tempi = child.getInfo();
        int tempp = child.getPriority();
        child.setPriority(parent.getPriority());
        child.setInfo(parent.getInfo());
        parent.setPriority(tempp);
        parent.setInfo(tempi);
    }

}
