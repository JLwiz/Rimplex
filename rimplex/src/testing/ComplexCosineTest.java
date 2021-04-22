package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import calculations.ComplexCosine;
import calculations.ComplexNumber;
import calculations.Operations;

class ComplexCosineTest {

  @Test
  void cosineCalculateTest()
  {
    Operations operator = new ComplexCosine();
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
    
    // Evaluates to undefined
    ComplexNumber zeroOp = new ComplexNumber(0.0, 0.0);
    Assertions.assertThrows(NumberFormatException.class, () -> 
    {
      operator.calculate(zeroOp);
    });
    
    ComplexNumber twoOp = new ComplexNumber(1.0, 1.0);
    Assertions.assertThrows(NumberFormatException.class, () -> 
    {
      operator.calculate(twoOp, twoOp);
    });
    
    op = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(-1475.56318, 210.33638);
    actual = operator.calculate(op);
    testHelper(actual, expected);
    
    op = new ComplexNumber(0.0, 2.0);
    expected = new ComplexNumber(3.762195, 0.0);
    actual = operator.calculate(op);
    testHelper(actual, expected);

    op = new ComplexNumber(10.0, 10.0);
    expected = new ComplexNumber(-9240.89018, 5991.431182);
    actual = operator.calculate(op);
    testHelper(actual, expected);
  }
  
  @Test
  void cosineRandomTesting()
  {
    Double[] values = new Double[2];
    for (int i = 0; i < values.length; i++)
    {
      values[i] = 100 * Math.random();
    }
    ComplexNumber op = new ComplexNumber(values[0], values[1]);
    Double real = values[0];
    Double img = values[1];
    Double newReal = Math.cos(real) * Math.cosh(img);
    Double newImg = Math.sin(-real) * Math.sinh(img);
    Operations operator = new ComplexCosine();
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
