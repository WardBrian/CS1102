public class StackTest{

    public static void main(String[] args) {
        //IStack<String> stack1 = new ArrayStack<String>();
        IStack<String> stack1 = new LinkedListStack<String>();

        stack1.push("!!!!!");
        stack1.push("World");
        stack1.push("Hello");

        System.out.println(stack1.size());
        while(!stack1.isEmpty()){
            System.out.println(stack1.pop());
        }
        System.out.println(stack1.size());
    }
}
