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
    expected = new ComplexNumber(0.21917808219178,
                                 -0.082191780821918);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 0.0);
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 2.0);
    op2 = new ComplexNumber(3.0, 8.0);
    expected = new ComplexNumber(-0.21917808219178,
                                 -0.082191780821918);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);
  }

  @Test
  void divisionRandomTesting()
  {
    // Trickier than expected
  }

  /**
   * General testHelper for to test the new calculated ComplexNumber.
   * 
   * @param actual
   * @param expected
   */
  public void testHelper(final ComplexNumber actual, final ComplexNumber expected)
  {
    assertEquals(expected.getReal(), actual.getReal(), 0.00001);
    assertEquals(expected.getImaginary(), actual.getImaginary(), 0.00001);
  }

}