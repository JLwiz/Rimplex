package testing;

<<<<<<< HEAD
import calculations.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Jacob Lewis and Kenneth Painter
 * @version 03.24.2021
 */
class ComplexSquareRootTest
{

  @Test
  void multiplicationCalculateTest()
  {
    Operations operator = new ComplexSquareRoot();
    ComplexNumber op1;
=======
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import calculations.ComplexMultiplication;
import calculations.ComplexNumber;
import calculations.ComplexSquareRoot;
import calculations.Operations;

class ComplexSquareRootTest 
{

  @Test
  void squareRootCalculateTest()
  {
    Operations operator = new ComplexSquareRoot();
    ComplexNumber op1;
    ComplexNumber op2;
>>>>>>> ad819570b421d792ef39a299f6af2509a933f5d1
    ComplexNumber expected;
    ComplexNumber actual;

    op1 = null;
<<<<<<< HEAD
    expected = new ComplexNumber(Double.NaN, Double.NaN);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(2.4025, -1.6649);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(3.0, 2.0);
    expected = new ComplexNumber(1.8174, 0.5503);
=======
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1);
    assertNull(actual);
    
    op1 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(2.4024991, -1.664932);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(2.0, 0.0);
    expected = new ComplexNumber(1.414213, 0.0);
>>>>>>> ad819570b421d792ef39a299f6af2509a933f5d1
    actual = operator.calculate(op1);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 0.0);
<<<<<<< HEAD
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1);
    testHelper(actual, expected);

    op1 = new ComplexNumber(0.0, 2.0);
    expected = new ComplexNumber(1.0, 1.0);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(-3.0, 2.0);
    expected = new ComplexNumber(0.5503, 1.8174);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(9.0, 9.0);
    expected = new ComplexNumber(3.2961, 1.3653);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
  }
  
  @Test
  void SquareRootRandomTesting()
  {
    for (int i = 0; i < 100; i++)
    {
      Double[] values = new Double[2];
      for (int z = 0; z < values.length; z++)
      {
        values[z] = 100 * Math.random();
      }
      Operations operator = new ComplexSquareRoot();
      Operations square = new ComplexExponentiation();
      ComplexNumber expected = new ComplexNumber(values[0], values[1]);
      ComplexNumber squared = square.calculate(expected, new ComplexNumber(2.0, 0.0));
      ComplexNumber actual = operator.calculate(squared);
      testHelper(actual, expected);
    }
  }
  
=======
    op2 = new ComplexNumber(3.0, -8.0);
    expected = new ComplexNumber(0.0, 0.0);
    actual = operator.calculate(op1, op2);
    testHelper(actual, expected);

    op1 = new ComplexNumber(-2.0, -3.0);
    expected = new ComplexNumber(0.8959774, -1.674149);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
    
    op1 = new ComplexNumber(-2.0, 3.0);
    expected = new ComplexNumber(0.8959774, 1.674149);
    actual = operator.calculate(op1);
    testHelper(actual, expected);
  }

//  @Test
//  void multiplicationRandomTesting()
//  {
//    Double[] values = new Double[4];
//    for (int i = 0; i < values.length; i++)
//    {
//      values[i] = 100 * Math.random();
//    }
//    ComplexNumber op1 = new ComplexNumber(values[0], values[1]);
//    ComplexNumber op2 = new ComplexNumber(values[2], values[3]);
//    Double real = (values[0] * values[2]) + (values[1] * values[3] * -1);
//    Double img = (values[0] * values[3]) + (values[1] * values[2]);
//    Operations operator = new ComplexMultiplication();
//    ComplexNumber expected = new ComplexNumber(real, img);
//    ComplexNumber actual = operator.calculate(op1, op2);
//    testHelper(actual, expected);
//  }
  

>>>>>>> ad819570b421d792ef39a299f6af2509a933f5d1
  /**
   * General testHelper for to test the new calculated ComplexNumber.
   * 
   * @param actual
   * @param expected
   */
  public void testHelper(final ComplexNumber actual, final ComplexNumber expected)
  {
<<<<<<< HEAD
    assertEquals(actual.getReal(), expected.getReal(), 0.0001);
    assertEquals(actual.getImaginary(), expected.getImaginary(), 0.0001);
  }
=======
    assertEquals(actual.getReal(), expected.getReal(), .001);
    assertEquals(actual.getImaginary(), expected.getImaginary(), .001);
  }

>>>>>>> ad819570b421d792ef39a299f6af2509a933f5d1
}
