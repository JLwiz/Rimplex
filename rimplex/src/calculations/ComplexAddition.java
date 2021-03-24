package calculations;

/**
 * ComplexNumber addition class.
 * 
 * @author Jacob Lewis
 * @version 03.24.2021
 */
public class ComplexAddition implements Operations
{
  /**
   * Complex Multiplication Constructor.
   */
  public ComplexAddition()
  {
  }

  /**
   * Does ComplexNumber addition.
   * 
   * @param op1
   *          ComplexNumber1
   * @param op2
   *          ComplexNumber2
   * @return ComplexNumber post addition.
   */
  @Override
  public ComplexNumber calculate(final ComplexNumber op1, final ComplexNumber op2)
  {
    Double realSum = 0.0;
    Double imgSum = 0.0;
    if (op1 != null)
    {
      realSum += op1.getReal();
      imgSum += op1.getImaginary();
    }
    if (op2 != null)
    {
      realSum += op2.getReal();
      imgSum += op2.getImaginary();
    }
    return new ComplexNumber(realSum, imgSum);
  }

}
