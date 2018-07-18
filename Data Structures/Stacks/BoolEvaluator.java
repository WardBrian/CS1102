import java.util.StringTokenizer;

public class BoolEvaluator{

    public static boolean isValue(String token){
        return (!token.equals("(") && !token.equals(")")
             && !token.equals("!") && !token.equals("&&")
             && !token.equals("||"));
    }

    public static boolean Eval(String expression){
        IStack<Boolean> vals = new LinkedListStack<Boolean>();
        IStack<String> ops = new LinkedListStack<String>();

        StringTokenizer tokens = new StringTokenizer(expression);

        while(tokens.hasMoreTokens()){
            String token = tokens.nextToken();

            if(isValue(token)) vals.push(Boolean.parseBoolean(token));
            else if(token.equals("(")) /* no-op */;
            else if(token.equals("!") || token.equals("&&")
                 || token.equals("||")) ops.push(token);
            else if(token.equals(")")){
                boolean v, u;
                String op = ops.pop();
                boolean result = false;

                if(op.equals("!")){
                    v = vals.pop();
                    result = !v;
                }
                else if(op.equals("&&")) {
                    v = vals.pop();
                    u = vals.pop();
                    result = v && u;
                }
                else if(op.equals("||")) {
                    v = vals.pop();
                    u = vals.pop();
                    result = v || u;
                }

                vals.push(result);
            }
        }

        return vals.pop();
    }

    public static void main(String[] args) {
        String exp = "( ( ! ( true && ( ! false ) ) ) || false )";
        System.out.println(Eval(exp));
        // exp = "( ( ( ( 2 * 16 ) + ( 7 * 4 ) ) * 2 ) - 17 )";
        // System.out.println(Eval(exp));
    }

}
