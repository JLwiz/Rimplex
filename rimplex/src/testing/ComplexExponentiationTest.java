package testing;

import calculations.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Jacob Lewis and Kenneth Painter
 * @version 03.24.2021
 */
class ComplexExponentiationTest
{

  @Test
  void multiplicationCalculateTest()
  {
    Operations operator = new ComplexExponentiation();
    ComplexNumber op1;
    ComplexNumber op2;
    ComplexNumber expected;
    ComplexNumber actual;

    op1 = null;
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(Double.NaN, Double.NaN);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(3.0, -8.0);
    op2 = null;
    expected = new ComplexNumber(Double.NaN, Double.NaN);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(3.0, 2.0);
    op2 = new ComplexNumber(3.0, 0.0);
    expected = new ComplexNumber(-9.0, 46.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 0.0);
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, 0.0);
    expected = new ComplexNumber(0.0, -8.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    actual = operator.calculate(null);
    expected = new ComplexNumber(0.0, 0.0);
    testHelper(actual, expected);
    
    actual = operator.calculate(null, null, null);
    expected = new ComplexNumber(0.0, 0.0);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(0.0, 2.9);
    actual = operator.calculate(op1, op2, op1);
    testHelper(actual, expected);
    
    actual = operator.calculate(null, op1);
    expected = new ComplexNumber(Double.NaN, Double.NaN);
    testHelper(actual, expected);
  }
  /*
  @Test
  void ExponentRandomTesting()
  {
    Double[] values = new Double[4];
    for (int i = 0; i < values.length; i++)
    {
      values[i] = 100 * Math.random();
    }
    ComplexNumber expected = new ComplexNumber(real, img);
    ComplexNumber actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
  }
  */
  
  /**
   * General testHelper for to test the new calculated ComplexNumber.
   * 
   * @param actual
   * @param expected
   */
  public void testHelper(final ComplexNumber actual, final ComplexNumber expected)
  {
    assertEquals(actual.getReal(), expected.getReal());
    assertEquals(actual.getImaginary(), expected.getImaginary());
  }
}
