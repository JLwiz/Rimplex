package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculations.ComplexAddition;
import calculations.ComplexNumber;
import calculations.Operations;

class ComplexAdditionTest
{
  @Test
  void additionCalculateTest()
  {
    Operations operator = new ComplexAddition();
    ComplexNumber op1;
    ComplexNumber op2;
    ComplexNumber op3;
    ComplexNumber expected;
    ComplexNumber actual;

    op1 = new ComplexNumber(0.0, 4.9);
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
    
    op1 = null;
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(3.0, -8.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(3.0, -8.0);
    op2 = null;
    expected = new ComplexNumber(3.0, -8.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    op1 = null;
    op2 = null;
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(3.0, -6.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 0.0);
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(3.0, -8.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, 8.0);
    expected = new ComplexNumber(3.0, 10.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, 8.0);
    op3 = new ComplexNumber(4.0, 12.0);
    expected = new ComplexNumber(0.0, 0.0); // fix after correction to addition
    actual = operator.calculate(op1, op2, op3);
    testHelper(actual, expected);
    
    op1 = null;
    op2 = new ComplexNumber(3.0, 8.0);
    op3 = new ComplexNumber(4.0, 12.0);
    expected = new ComplexNumber(0.0, 0.0); // fix after correction to addition
    actual = operator.calculate(op1, op2, op3);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(3.0, 7.0);
    op2 = null;
    op3 = new ComplexNumber(4.0, 12.0);
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2, op3);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(3.0, 7.0);
    op2 = new ComplexNumber(4.0, 3.0);
    op3 = null;
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2, op3);
    testHelper(actual, expected);
    
    op1 = null;
    op2 = null;
    op3 = null;
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2, op3);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(0.0, 0.0);
    op2 = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    op3 = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2, op3);
    testHelper(actual, expected);
    
    op1 = null;
    op2 = null;
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
    
    op3 = null;
    actual = operator.calculate(op1, op2, op3);
    testHelper(actual, expected);
    
    ComplexNumber op4 = null;
    actual = operator.calculate(op1, op2, op3, op4);
    testHelper(actual, expected);
    
    actual = operator.calculate(op1);
    testHelper(actual, expected);
    
    actual = operator.calculate(null);
    testHelper(actual, expected);
    
    
  }

  @Test
  void additionRandomTesting()
  {
    Double[] values = new Double[4];
    for (int i = 0; i < values.length; i++)
    {
      values[i] = 100 * Math.random();
    }
    ComplexNumber op1 = new ComplexNumber(values[0], values[1]);
    ComplexNumber op2 = new ComplexNumber(values[2], values[3]);
    Double real = (values[0] + values[2]);
    Double img = (values[1] + values[3]);
    Operations operator = new ComplexAddition();
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
