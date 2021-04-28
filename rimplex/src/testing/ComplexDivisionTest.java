package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculations.ComplexDivision;
import calculations.ComplexNumber;
import calculations.Operations;

class ComplexDivisionTest
{
  @Test
  void divisionCalculateTest()
  {
    Operations operator = new ComplexDivision();
    ComplexNumber op1;
    ComplexNumber op2;
    ComplexNumber expected;
    ComplexNumber actual;

    op1 = null;
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(3.0, -8.0);
    op2 = null;

    // This is a dumb way to handle this. Discuss options with team.
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(-0.21917808219178, 0.082191780821918);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 0.0);
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, 8.0);
    expected = new ComplexNumber(0.21917808219178, 0.082191780821918);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(4.2, 6.1);
    op2 = new ComplexNumber(7.2, 10.5);
    expected = new ComplexNumber(0.58171386266889, -0.0011104941699056);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
  }

  @Test
  void divisionRandomTest()
  {
    Double[] values = new Double[50];
    for (int i = 0; i < values.length; i++)
    {
      values[i] = 100 * Math.random();
    }

    ComplexNumber op1 = new ComplexNumber(values[0], values[1]);
    ComplexNumber op2 = new ComplexNumber(values[2], values[3]);

    Double op1Real = values[0];
    Double op2Real = values[2];
    Double op1Imag = values[1];
    Double op2Imag = values[3];

    Double real = (op1Real * op2Real + op1Imag * op2Imag) / (op2Real * op2Real + op2Imag * op2Imag);
    Double img = (op1Imag * op2Real - op1Real * op2Imag) / (op2Real * op2Real + op2Imag * op2Imag);

    Operations operator = new ComplexDivision();
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
    assertEquals(expected.getReal(), actual.getReal(), 0.0000001);
    assertEquals(expected.getImaginary(), actual.getImaginary(), 0.0000001);
  }

}
