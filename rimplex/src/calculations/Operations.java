package calculations;

/**
 * @author Jacob Lewis
 * @version 03.24.2021
 */
public abstract interface Operations 
{
//  /**
//   * Operations calculate class for complex numbers.
//   * @param op1 leftOperand
//   * @param op2 currOperand
//   * @return ComplexNumber
//   */
//  public ComplexNumber calculate(ComplexNumber op1, ComplexNumber op2);
//  
  /**
   * Operations calculate class for complex numbers.
   * 
   * The benefit of using this over hard setting attributes is that its
   * more dynamic and allows unitary operators to be implemented more easily.
   * @param op variable # of objects
   * @return ComplexNumber
   */
  public ComplexNumber calculate(ComplexNumber... op);

}
