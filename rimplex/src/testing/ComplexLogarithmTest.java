package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import calculations.ComplexLogarithm;
import calculations.ComplexNumber;
import calculations.Operations;

/**
 * Complex number logarithm testclass.
 * 
 * @author Jacob Lewis
 * @version 04.05.2021
 */
class ComplexLogarithmTest 
{

  @Test
  void logarithmCalculateTest()
  {
    Operations operator = new ComplexLogarithm();
    ComplexNumber op;
    ComplexNumber expected;
    ComplexNumber actual;

    ComplexNumber nullOp = null;
    Assertions.assertThrows(NumberFormatException.class, () -> 
    {
      operator.calculate(nullOp);
    });
    Assertions.assertThrows(NumberFormatException.class, () -> 
    {
      operator.calculate(null);
    });
    
    // Makes a weird imaginary and real number for log of zero.
    ComplexNumber zeroOp = new ComplexNumber(0.0, 0.0);
    Assertions.assertThrows(NumberFormatException.class, () -> 
    {
      operator.calculate(zeroOp);
    });

    op = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(2.1452297205741955, -1.2120256565243244);
    // 2.1452297-1.2120257i
    actual = operator.calculate(op);
    testHelper(actual, expected);
    
    op = new ComplexNumber(0.0, 2.0);
    expected = new ComplexNumber(0.693, 1.570);
    // 0.6931472+1.5707963i
    actual = operator.calculate(op);
    testHelper(actual, expected);

    op = new ComplexNumber(24.0, 72.0);
    expected = new ComplexNumber(4.329, 1.249);
    actual = operator.calculate(op);
    testHelper(actual, expected);
  }

  @Test
  void logarithmRandomTesting()
  {
    Double[] values = new Double[2];
    for (int i = 0; i < values.length; i++)
    {
      values[i] = 100 * Math.random();
    }
    ComplexNumber op = new ComplexNumber(values[0], values[1]);
    Double real = values[0];
    Double img = values[1];
    Double newReal = (1.0/2.0) * Math.log((real * real) + (img * img));
    Double newImg = Math.atan(img/real);
    Operations operator = new ComplexLogarithm();
    ComplexNumber expected = new ComplexNumber(newReal, newImg);
    ComplexNumber actual = operator.calculate(op);
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
    assertEquals(actual.getReal(), expected.getReal(), .001);
    assertEquals(actual.getImaginary(), expected.getImaginary(), .001);
  }

}
