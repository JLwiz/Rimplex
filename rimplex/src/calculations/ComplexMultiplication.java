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
   * Does ComplexNumber addition.
   * 
   * @param op1
   *          ComplexNumber1
   * @param op2
   *          ComplexNumber2
   * @return ComplexNumber post addition.
   */
  public ComplexNumber calculate(final ComplexNumber op1, final ComplexNumber op2)
  {

    Double realOne = 0.0;
    Double realTwo = 0.0;
    Double imgOne = 0.0;
    Double imgTwo = 0.0;
    if (op1 != null && op2 != null)
    {
      realOne = op1.getReal() * op2.getReal();
      realTwo = op1.getReal() * op2.getImaginary();
      imgOne = op1.getImaginary() * op2.getReal();
      imgTwo = op1.getImaginary() * op2.getImaginary() * -1;
    } 
    Double newReal = realOne + imgTwo;
    Double newImg = realTwo + imgOne;

    return new ComplexNumber(newReal, newImg);
  }

}
