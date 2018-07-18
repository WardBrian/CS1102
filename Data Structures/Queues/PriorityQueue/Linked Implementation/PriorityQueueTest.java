public class PriorityQueueTest{

    public static void main(String[] args) {
        IPriorityQueue<String> q = new LinkedPriorityQueue<String>();

        for(int i = 0; i < 15; i++){
            int j = /*20 - i;//*/(int)(Math.random()*1000) - 500;
            q.add(Integer.toString(j),j);
        }

        System.out.println("Size is " + q.size());

        while(!q.isEmpty()){
            System.out.println(q.remove());
        }
        System.out.println("Size is " + q.size());
        System.out.println(q.remove());
    }

}
