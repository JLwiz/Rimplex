package testing;

import calculations.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Jacob Lewis
 * @version 03.24.2021
 */
class ComplexMultiplicationTest
{

  @Test
  void multiplicationCalculateTest()
  {
    Operations operator = new ComplexMultiplication();
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
    
    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(16.0, 6.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 0.0);
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, 8.0);
    expected = new ComplexNumber(-16.0, 6.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
  }

  @Test
  void multiplicationRandomTesting()
  {
    Double[] values = new Double[4];
    for (int i = 0; i < values.length; i++)
    {
      values[i] = 100 * Math.random();
    }
    ComplexNumber op1 = new ComplexNumber(values[0], values[1]);
    ComplexNumber op2 = new ComplexNumber(values[2], values[3]);
    Double real = (values[0] * values[2]) + (values[1] * values[3] * -1);
    Double img = (values[0] * values[3]) + (values[1] * values[2]);
    Operations operator = new ComplexMultiplication();
    ComplexNumber expected = new ComplexNumber(real, img);
    ComplexNumber actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
  }

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
