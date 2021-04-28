package calculations;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * A class for calculating the cosine of a complex number.
 * 
 * @author Jacob Lewis
 * @version 4/27/2021
 */
public class ComplexCosine implements Operations
{

  /**
   * Calculates the cosine of a complex number.
   * 
   * @return returns a complex number
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

    // can't take log of zero, so an exception needs to be raised.
    if (hold.getReal() == 0 && hold.getImaginary() == 0)
    {
      throw new NumberFormatException();
    }
    double real = hold.getReal();
    double img = hold.getImaginary();
    Double newReal = Math.cos(real) * Math.cosh(img);
    Double newImg = Math.sin(-1 * real) * Math.sinh(img);
    answer = new ComplexNumber(newReal, newImg);

    return answer;
  }
}
