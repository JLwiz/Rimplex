package calculations;

/**
 * Complex number multiplication class.
 * 
 * @author Jacob Lewis
 * @version 03.24.2021
 */
public class ComplexMultiplication implements Operations
{
  /**
   * Complex Multiplication Constructor.
   */
  public ComplexMultiplication()
  {
  }

  /**
   * Does ComplexNumber multiplication.
   * 
   * @param op1
   *          ComplexNumber1
   * @param op2
   *          ComplexNumber2
   * @return ComplexNumber post multiplication.
   */
  public ComplexNumber calculate(final ComplexNumber op1, final ComplexNumber op2)
  {
    Double newReal = 0.0;
    Double newImg = 0.0;
    if (op1 != null && op2 != null)
    {
      newReal += op1.getReal() * op2.getReal();
      newReal += op1.getImaginary() * op2.getImaginary() * -1;
      newImg += op1.getReal() * op2.getImaginary();
      newImg += op1.getImaginary() * op2.getReal();
    }
    return new ComplexNumber(newReal, newImg);
  }

}
