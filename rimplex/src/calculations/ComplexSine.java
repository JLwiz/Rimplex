package calculations;

/**
 * Calculates the sine of a complex number.
 * 
 * @author ben76
 * @version 4/27/2021
 */
public class ComplexSine implements Operations
{

  /**
   * Calculates the sine of a complex number.
   * 
   * @return the result of a sine operation on a ComplexNumber
   */
  @Override
  public ComplexNumber calculate(final ComplexNumber... op)
  {
    ComplexNumber answer = new ComplexNumber(0.0, 0.0);
    ComplexNumber hold = answer;
    if (op != null && op.length == 1)
    {
      if (op[0] != null)
      {
        hold = op[0];
      }
    }
    if (hold.getReal() == 0 && hold.getImaginary() == 0)
    {
      throw new NumberFormatException();
    }
    double img = hold.getImaginary();
    double real = hold.getReal();
    double realSine = Math.sin(real);
    double imgCosh = Math.cosh(img);
    double realCosine = Math.cos(real);
    double imgSinh = Math.sinh(img);
    
    Double newReal = realSine * imgCosh;
    Double newImg = realCosine * imgSinh;
    answer = new ComplexNumber(newReal, newImg);

    return answer;
  }

}
