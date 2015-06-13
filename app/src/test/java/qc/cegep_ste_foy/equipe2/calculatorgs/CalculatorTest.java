package qc.cegep_ste_foy.equipe2.calculatorgs;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Vicente on 5/6/2015.
 */
public class CalculatorTest extends TestCase {

    Calculator calculator;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        calculator = new Calculator();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testSetEquation() throws Exception {
        assertTrue(true);
    }

    @Test
    public void test2Plus8IsTen() throws Exception {
        double expected = 10;
        calculator.setEquation("2+8");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void test2x8Is16() throws Exception {
        double expected = 16;
        calculator.setEquation("2*8");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void test8Minus2Is6() throws Exception {
        double expected = 6;
        calculator.setEquation("8-2");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void test8DivideBy2Is4() throws Exception {
        double expected = 4;
        calculator.setEquation("8/2");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void testLogOf10Is2() throws Exception {
        double expected = 2.302585092994046;
        calculator.setEquation("log(10)");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void testLog10Of10Is1() throws Exception {
        double expected = 1;
        calculator.setEquation("log10(10)");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void testSinOf10IsValid() throws Exception {
        double expected = -0.5440211108893698;
        calculator.setEquation("sin(10)");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void testCosOf10IsValid() throws Exception {
        double expected = -0.8390715290764524;
        calculator.setEquation("cos(10)");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void testTanOf10IsValid() throws Exception {
        double expected = 0.6483608274590866;
        calculator.setEquation("tan(10)");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void testFactorial4Is24() throws Exception {
        double expected = 24;
        calculator.setEquation("4!");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void test10Modulo4Is2() throws Exception {
        double expected = 2;
        calculator.setEquation("10%4");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void testBasicPriority() throws Exception {
        double expected = 23;
        calculator.setEquation("2+4*7-10+9/3");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }

    @Test
    public void testParenthesePriority() throws Exception {
        double expected = 35;
        calculator.setEquation("(2+4)*7-10+9/3");

        calculator.calculate();
        double result = calculator.getResult();

        assertEquals(expected, result);
    }


}