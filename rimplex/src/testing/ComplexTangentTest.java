package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import calculations.ComplexCosine;
import calculations.ComplexDivision;
import calculations.ComplexNumber;
import calculations.ComplexSine;
import calculations.ComplexTangent;
import calculations.Operations;

class ComplexTangentTest {

  @Test
  void tangentCalculateTest()
  {
    Operations operator = new ComplexTangent();
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
    expected = new ComplexNumber(.0000001, -0.9999997);
    actual = operator.calculate(op);
    testHelper(actual, expected);
    
    op = new ComplexNumber(0.0, 2.0);
    expected = new ComplexNumber(0.0, 0.9640275);
    actual = operator.calculate(op);
    testHelper(actual, expected);

    op = new ComplexNumber(10.0, 10.0);
    expected = new ComplexNumber(0.000000003, .9999999983177);
    actual = operator.calculate(op);
    testHelper(actual, expected);
  }
  
  @Test
  void tangentRandomTesting()
  {
    Double[] values = new Double[2];
    for (int i = 0; i < values.length; i++)
    {
      values[i] = 100 * Math.random();
    }
    ComplexNumber op = new ComplexNumber(values[0], values[1]);
    Double real = values[0];
    Double img = values[1];
    Double numReal = Math.sin(real) * Math.cosh(img);
    Double numImg = Math.cos(real) * Math.sinh(img);
    ComplexNumber numerator = new ComplexNumber(numReal, numImg);
    Double demReal = Math.cos(real) * Math.cosh(img);
    Double demImg = Math.sin(real) * Math.sinh(img);
    ComplexNumber denominator = new ComplexNumber(demReal, -demImg);
    
    Operations divide = new ComplexDivision();
    Operations tan = new ComplexTangent();
    ComplexNumber tangent = divide.calculate(numerator, denominator);
    Double newReal = tangent.getReal();
    Double newImg = tangent.getImaginary();
    ComplexNumber expected = new ComplexNumber(newReal, newImg);
    ComplexNumber actual = tan.calculate(op);
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
