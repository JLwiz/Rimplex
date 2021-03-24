package testing;

import calculations.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexMultiplicationTest
{

  @Test
  void multiCalculateTest()
  {
    Operations op = new ComplexMultiplication();
    ComplexNumber op1 = new ComplexNumber(0.0, 2.0);
    ComplexNumber op2 = new ComplexNumber(3.0, -8.0);
    ComplexNumber expected = new ComplexNumber(16.0, 6.0);
    ComplexNumber actual = op.calculate(op1, op2);
    assertEquals(actual.getReal(), expected.getReal());
    assertEquals(actual.getImaginary(), expected.getImaginary());
  }
  
  /**
   * General testHelper for to test the new calculated ComplexNumber.
   * @param actual
   * @param expected
   */
  public void testHelper(final ComplexNumber actual, final ComplexNumber expected)
  {
    assertEquals(actual.getReal(), expected.getReal());
    assertEquals(actual.getImaginary(), expected.getImaginary());
  }
}
