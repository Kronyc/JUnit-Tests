package sample;

/**
 * Created by admin on 14.10.2014.
 */
import org.junit.Test;
public class Controller {

    private static final char[] opers = {'(','+','-','*','/'};
    private static int findOperatorPos(String string){
        int index = -1;
        for(int i = 0; i <= opers.length-1; i++)
        {
            index = string.lastIndexOf(opers[i]);
            if(index >= 0){
                return index;
            }
        }
        return index;
    }
    public static String eval(String equation){
        String left, right, middle;
        Float res;
        int operatorLoc = findOperatorPos(equation);

        if( operatorLoc >= 0)
        {
            left = equation.substring(0, operatorLoc);
            right = equation.substring(operatorLoc + 1);
            char oper = equation.charAt(operatorLoc);
            switch (oper) {
                case '(':
                    res =  Float.parseFloat(eval(equation.substring(0, operatorLoc) + eval(equation.substring(operatorLoc + 1, equation.indexOf(")", operatorLoc))) + equation.substring(equation.indexOf(")", operatorLoc)+1)));
                    return Float.toString(res);
                case '+':
                    res = Float.parseFloat(eval(left)) + Float.parseFloat(eval(right));
                    return Float.toString(res);
                case '-':
                    res = Float.parseFloat(eval(left)) - Float.parseFloat(eval(right));
                    return Float.toString(res);
                case '*':
                    res = Float.parseFloat(eval(left)) * Float.parseFloat(eval(right));
                    return Float.toString(res);
                case '/':
                    res = Float.parseFloat(eval(left)) / Float.parseFloat(eval(right));
                    return Float.toString(res);
            }
        }
        else {
            return equation;
        }
        return eval(equation);
    }

    @Test
    public void testAssertEquals() {
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("2*(3+4)"), Float.toString(14));
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("2+3*4/2+1024+1024/2*6"), Float.toString(4104));
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("1-(2+3)*2"), Float.toString(-9));
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("10-(4*(2+2)+1)"), Float.toString(-7));
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("11+((6+1)+2*1)+200"), Float.toString(220));
    }

    @Test
    public void testAsserEqualsSecondSix() {
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("6/6/6"), Double.toString(0.16666667));

    }
    @Test
    public void testAssertNotNull() {
        org.junit.Assert.assertNotNull("should not be null", eval("10-4*2+2/11"));
    }

    @Test
    public void testAssertNull() {
        org.junit.Assert.assertNull("should be null", null);
    }
}