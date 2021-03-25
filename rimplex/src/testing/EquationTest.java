package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculations.ComplexNumber;
import calculations.Equation;

/**
 * Class to test the Equation class.
 * 
 * @author Andrew Fryer
 * @version 1.0 (03/24/2023)
 */
class EquationTest
{
 
  /**
   * operandEmptyTest - Will test the operandEmpty method.
   */
  @Test
  void operandEmptyTest()
  {
    Equation.getInstance().setFirstOp(null);
    assertTrue(Equation.getInstance().operandEmpty());
    
    Equation.getInstance().setFirstOp(new ComplexNumber(2.1, 4.1));
    
    assertFalse(Equation.getInstance().operandEmpty());
  } // operandEmptyTest test.
  
  /**
   * operatorEmptyTest - Will test the operatorEmpty method.
   */
  @Test
  void operatorEmptyTest()
  {
    Equation.getInstance().setFirstOp(null);
    assertTrue(Equation.getInstance().operatorEmpty());
    
    Equation.getInstance().setOperator("+");
    
    assertFalse(Equation.getInstance().operatorEmpty());
  } // operatorEmptyTest test.
  
  /**
   * setOperandNullTests - Will test the setFirstOp and setSecondOp methods
   * when the given parameter is null.
   */
  @Test
  void setOperandNullTests()
  {
    Equation.getInstance().setFirstOp(null);
    Equation.getInstance().setSecondOp(null);
    
    assertEquals(0.0, Equation.getInstance().solve().getImaginary());
    assertEquals(0.0, Equation.getInstance().solve().getReal());
  } // setOperandTests test.
  
  /**
   * setOperandValidTests - Will test the setFirstOp and setSecondOp methods
   * when the given parameter is valid.
   */
  @Test
  void setOperandValidTests()
  {
    Equation.getInstance().setFirstOp(new ComplexNumber(2.0, 2.0));
    Equation.getInstance().setSecondOp(new ComplexNumber(2.0, 2.0));
    Equation.getInstance().setOperator("+");
    
    ComplexNumber result = Equation.getInstance().solve();
    
    assertEquals(4.0, result.getImaginary());
    assertEquals(4.0, result.getReal());
  } // setOperandValidTests test.
  
  /**
   * setOperatorInvalidTest - Will test the setOperator method
   * with an invalid parameter.
   */
  @Test
  void setOperatorInvalidTest()
  {
    Equation.getInstance().setOperator(" ");
    Equation.getInstance().setFirstOp(new ComplexNumber(2.0, 2.0));
    Equation.getInstance().setSecondOp(new ComplexNumber(2.0, 2.0));
    
    assertEquals(0.0, Equation.getInstance().solve().getImaginary());
    assertEquals(0.0, Equation.getInstance().solve().getReal());
    
    Equation.getInstance().setOperator("8");
    
    assertEquals(0.0, Equation.getInstance().solve().getImaginary());
    assertEquals(0.0, Equation.getInstance().solve().getReal());
  } // setOperatorInvalidTest method.
  
  /**
   * setOperatorNullTest - Will test the setOperator method
   * with a null parameter.
   */
  @Test
  void setOperatorNullTest()
  {
    Equation.getInstance().setOperator(null);
    Equation.getInstance().setFirstOp(new ComplexNumber(2.0, 2.0));
    Equation.getInstance().setSecondOp(new ComplexNumber(2.0, 2.0));
    
    assertEquals(0.0, Equation.getInstance().solve().getImaginary());
    assertEquals(0.0, Equation.getInstance().solve().getReal());
  } // setOperatorNullTest test.
  
  /**
   * setOperatorValidTest - Will test the setOperator method
   * with a valid parameter.
   */
  @Test
  void setOperatorValidTest()
  {
    Equation.getInstance().setOperator("-");
    Equation.getInstance().setFirstOp(new ComplexNumber(2.0, 2.0));
    Equation.getInstance().setSecondOp(new ComplexNumber(2.0, 2.0));
    
    ComplexNumber result = Equation.getInstance().solve();
    
    assertEquals(0.0, result.getImaginary());
    assertEquals(0.0, result.getReal());
  } // setOperatorValidTest test.
  
  /**
   * singletonTest - Will make sure that there is only one 
   * instance of the Equation.
   */
  @Test
  void singletonTest()
  {
    assertEquals(Equation.getInstance(), Equation.getInstance());
  } // singletonTest test.
  
  /**
   * solveAdditionTest - Will test the solve method with addition.
   */
  @Test
  void solveAdditionTest()
  {
    ComplexNumber result;
    
    Equation.getInstance().setOperator("+");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(4.2, 6.1));
    Equation.getInstance().setSecondOp(new ComplexNumber(7.1, 10.5));
    
    result = Equation.getInstance().solve();
    
    assertEquals(16.6, result.getImaginary(), 0.01);
    assertEquals(11.3, result.getReal(), 0.01);
    
    Equation.getInstance().setOperator("+");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(6.7, -7.3));
    Equation.getInstance().setSecondOp(new ComplexNumber(8.0, 2.9));
    
    result = Equation.getInstance().solve();
    
    assertEquals(-4.4, result.getImaginary(), 0.01);
    assertEquals(14.7, result.getReal(), 0.01);
    
    Equation.getInstance().setOperator("+");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(0.5, 9.7));
    Equation.getInstance().setSecondOp(new ComplexNumber(20.3, -5.4));
    
    result = Equation.getInstance().solve();
    
    assertEquals(4.3, result.getImaginary(), 0.01);
    assertEquals(20.8, result.getReal(), 0.01);
    
  } // solveAdditionTest test.
  
  /**
   * solveDivisionTest - Will test the solve method with division.
   */
  @Test
  void solveDivisionTest()
  {
    ComplexNumber result;
    
    Equation.getInstance().setOperator("÷");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(4.2, 6.1));
    Equation.getInstance().setSecondOp(new ComplexNumber(7.1, 10.5));
    
    result = Equation.getInstance().solve();
    
    assertEquals(-0.00491, result.getImaginary(), 0.01);
    assertEquals(0.5842, result.getReal(), 0.01);
    
    Equation.getInstance().setOperator("÷");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(6.7, -7.3));
    Equation.getInstance().setSecondOp(new ComplexNumber(8.0, 2.9));
    
    result = Equation.getInstance().solve();
    
    assertEquals(-1.07485, result.getImaginary(), 0.01);
    assertEquals(0.44785, result.getReal(), 0.01);
    
    Equation.getInstance().setOperator("÷");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(0.5, 9.7));
    Equation.getInstance().setSecondOp(new ComplexNumber(20.3, -5.4));
    
    result = Equation.getInstance().solve();
    
    assertEquals(0.45237, result.getImaginary(), 0.01);
    assertEquals(-0.09570, result.getReal(), 0.01);
  } // solveDivisionTest test.
  
  /**
   * solveMultiplicationTest - Will test the solve method with multiplication.
   */
  @Test
  void solveMultiplicationTest()
  {
    ComplexNumber result;
    
    Equation.getInstance().setOperator("×");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(4.2, 6.1));
    Equation.getInstance().setSecondOp(new ComplexNumber(7.1, 10.5));
    
    result = Equation.getInstance().solve();
    
    assertEquals(87.47, result.getImaginary(), 0.1);
    assertEquals(-34.23, result.getReal(), 0.1);
    
    Equation.getInstance().setOperator("×");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(6.7, -7.3));
    Equation.getInstance().setSecondOp(new ComplexNumber(8.0, 2.9));
    
    result = Equation.getInstance().solve();
    
    assertEquals(-38.97, result.getImaginary(), 0.1);
    assertEquals(74.77, result.getReal(), 0.1);
    
    Equation.getInstance().setOperator("×");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(0.5, 9.7));
    Equation.getInstance().setSecondOp(new ComplexNumber(20.3, -5.4));
    
    result = Equation.getInstance().solve();
    
    assertEquals(194.21, result.getImaginary(), 0.1);
    assertEquals(62.53, result.getReal(), 0.1);
  } // solveMultiplicationTest test.
  
  /**
   * solveSetsTest - Will test that the solve method correctly sets
   * operands and operator after solving.
   */
  @Test
  void solveSetsTest()
  {
    Equation.getInstance().setOperator("+");
    Equation.getInstance().setFirstOp(new ComplexNumber(2.0, 2.0));
    Equation.getInstance().setSecondOp(new ComplexNumber(2.0, 2.0));
    
    ComplexNumber result = Equation.getInstance().solve();
    assertEquals(Equation.getInstance().getFirstOp().getReal(), result.getReal());
    assertEquals(Equation.getInstance().getFirstOp().getImaginary(), result.getImaginary());
  } // solveSetsTest test.
  
  /**
   * solveSubtractionTest - Will test the solve method with subtraction.
   */
  @Test
  void solveSubtractionTest()
  {
    ComplexNumber result;
    
    Equation.getInstance().setOperator("-");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(4.2, 6.1));
    Equation.getInstance().setSecondOp(new ComplexNumber(7.1, 10.5));
    
    result = Equation.getInstance().solve();
    
    assertEquals(-4.4, result.getImaginary(), 0.01);
    assertEquals(-2.9, result.getReal(), 0.01);
    
    Equation.getInstance().setOperator("-");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(6.7, -7.3));
    Equation.getInstance().setSecondOp(new ComplexNumber(8.0, 2.9));
    
    result = Equation.getInstance().solve();
    
    assertEquals(-10.2, result.getImaginary(), 0.01);
    assertEquals(-1.3, result.getReal(), 0.01);
    
    Equation.getInstance().setOperator("-");
    
    Equation.getInstance().setFirstOp(new ComplexNumber(0.5, 9.7));
    Equation.getInstance().setSecondOp(new ComplexNumber(20.3, -5.4));
    
    result = Equation.getInstance().solve();
    
    assertEquals(15.1, result.getImaginary(), 0.01);
    assertEquals(-19.8, result.getReal(), 0.01);
  } // solveSubtractionTest test.
  
} // EquationTest class.
