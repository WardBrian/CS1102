import java.util.StringTokenizer;

public class Evaluator{

    public static boolean isNumber(String token){
        return (!token.equals("(") && !token.equals(")")
             && !token.equals("*") && !token.equals("/")
             && !token.equals("+") && !token.equals("-"));
    }

    public static double Eval(String expression){
        IStack<Double> vals = new LinkedListStack<Double>();
        IStack<String> ops = new LinkedListStack<String>();

        StringTokenizer tokens = new StringTokenizer(expression);

        while(tokens.hasMoreTokens()){
            String token = tokens.nextToken();

            if(isNumber(token)) vals.push(Double.parseDouble(token));
            else if(token.equals("(")) /* no-op */;
            else if(token.equals("+") || token.equals("-")
                 || token.equals("*") || token.equals("/")) ops.push(token);
            else if(token.equals(")")){
                double v = vals.pop();
                double u = vals.pop();
                String op = ops.pop();
                double result = 0;

                if(op.equals("+")) result = u + v;
                else if(op.equals("-")) result = u - v;
                else if(op.equals("*")) result = u * v;
                else if(op.equals("/")) result = u / v;

                vals.push(result);
            }
        }

        return vals.pop();
    }

    public static void main(String[] args) {
        String exp = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        System.out.println(Eval(exp));
        exp = "( ( ( ( 2 * 16 ) + ( 7 * 4 ) ) * 2 ) - 17 )";
        System.out.println(Eval(exp));
    }

}
