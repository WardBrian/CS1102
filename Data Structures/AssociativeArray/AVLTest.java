public class AVLTest{

    public static void main(String[] args) {
        IAssociativeArray<String, String> st = new AVLTree<String, String>();

        // insert some (key, value pairs)
        st.put("g", "good");
        st.put("c", "cantina");
        st.put("b", "banana");
        st.put("a", "apple");
        st.put("d","dog");
        st.put("s", "stop");
        st.put("k", "kiss");
        st.put("z", "zoo");
        st.put("x", "xylophone");
        st.put("y", "yellow");
        st.put("h", "hello");
        st.put("j", "jeep");
        st.put("i","indigo");
        System.out.println(st.get("a"));
        System.out.println(st.get("z"));
        System.out.println(st.get("p"));

         st.delete("z");
         st.delete("g");
         st.delete("c");

    }
}
