public class BSTTest{

    public static void main(String[] args) {
        RecursiveBST<Integer, Integer> st = new RecursiveBST<>();

        // insert some (key, value pairs)
        for(int i = 0; i < 10; i++){
            int j = (int)(Math.random()*100);
            st.put(j,j);
            System.out.println(j);
        }

        System.out.println("bound");

        System.out.println(st.bound(50));

    }
}
