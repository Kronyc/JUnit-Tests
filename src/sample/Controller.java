package sample;

import org.junit.Test;

public class Controller {

    private static final char[] opers = {'+','-','*','/'};
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
    public static float eval(String equation){
        int operatorLoc = findOperatorPos(equation);

        if( operatorLoc >= 0)
        {
            String left = equation.substring(0, operatorLoc);
            String right = equation.substring(operatorLoc + 1);
            char oper = equation.charAt(operatorLoc);
            switch (oper){
                case '+':
                    return eval(left) + eval(right);
                case '-':
                    return eval(left) - eval(right);
                case '*':
                    return eval(left) * eval(right);
                case '/':
                    return eval(left) / eval(right);
            }
        }
        else {
            return Float.parseFloat(equation);
        }

        return eval(equation);

    }
    @Test
    public void testAssertEquals() {
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("2+3*4"), 14, 1e-8);
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("2+3*4/2+1024+1024/2*6"), 4104, 1e-8);
    }
    @Test
    public void testAsserEqualsFirstSix() {
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("6/6/6"), 6, 1e-8);

    }
    @Test
    public void testAsserEqualsSecondSix() {
        org.junit.Assert.assertEquals("failure - strings are not equal", eval("6/6/6"), 0.1666666667, 1e-8);

    }
    @Test
    public void testAssertNotNull() {
        org.junit.Assert.assertNotNull("should not be null", eval("10-4*2+2/11"));
    }

    @Test
    public void testAssertNull() {
        org.junit.Assert.assertNull("should be null", eval("1+1+1+1+0"));
    }
}
