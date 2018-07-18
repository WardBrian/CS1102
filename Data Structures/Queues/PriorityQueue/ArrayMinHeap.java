public class ArrayMinHeap{
    private int[] pq;
    private int n;

    public ArrayMinHeap(int initialCapacity){
        pq = new int[initialCapacity + 1];
        n = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void insert(int x){
        if(n == pq.length) throw new java.util.NoSuchElementException("Queue full");
        pq[++n] = x;
        swim(n);
    }

    public int delMin(){
        if(isEmpty()) throw new java.util.NoSuchElementException("Queue empty");
        int min = pq[1];
        swap(1, n--);
        sink(1);
        return min;
    }

    public int min(){
        if(!isEmpty()) throw new java.util.NoSuchElementException("Queue empty");
        return pq[1];
    }

    private void swim(int i){
        while (i > 1 && greater(i/2, i)){
            swap(i, i/2);
            i /= 2;
        }
    }

    private void sink(int i){
        while(2*i <= n){
            int j = 2*i;
            if(j < n && greater(j, j+1)) j++; //chose other child
            if(!greater(i, j)) break; // only swap if i < j
            swap(i, j);
            i = j;
        }
    }

    private boolean greater(int i, int j){
        return pq[i] > pq[j];
    }

    private void swap(int i, int j){
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public void display(){
        for(int i = 1; i <= this.size(); i++){
            System.out.print(pq[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        ArrayMinHeap h = new ArrayMinHeap(100);
        for(int i = 0; i < 10; i++){
            h.insert((int)(Math.random()*100) - 50);
        }

        h.display();

        while(!h.isEmpty()){
            System.out.println(h.delMin());
        }
    }
}
