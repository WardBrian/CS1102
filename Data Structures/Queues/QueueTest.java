public class QueueTest{
    public static void main(String[] args) {
        IQueue<String> queue1 = new LinkedListQueue<String>();

        queue1.enqueue("Hello");
        queue1.enqueue("World");
        queue1.enqueue("!!!!");

        System.out.println("The queue has size " + queue1.size());

        while(!queue1.isEmpty()){
            System.out.println(queue1.dequeue());

        }
        System.out.println(queue1.dequeue());

        queue1.enqueue("Goodbye");
        System.out.println(queue1.dequeue());
    }
}
