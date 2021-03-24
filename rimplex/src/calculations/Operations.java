package calculations;

/**
 * @author Jacob Lewis
 * @version 03.24.2021
 */
public interface Operations 
{
  /**
   * Operations calculate class for complex numbers.
   * @param op1 leftOperand
   * @param op2 currOperand
   * @return ComplexNumber
   */
  public ComplexNumber calculate(ComplexNumber op1, ComplexNumber op2);

}
