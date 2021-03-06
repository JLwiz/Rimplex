package calculations;

/**
 * Complex number logarithm class.
 * 
 * @author Jacob Lewis
 * @version 04.05.2021
 */
public class ComplexLogarithm implements Operations 
{
  /*
   * ln(z) = ? z = a + bi r = sqrt(a^2 + b^2) theta = arctan(b/a) a+bi = r*cos(theta) + r*sin(theta)
   * = r(cos(theta) + i * sin(theta)) = (re)^(i(theta)
   * 
   * ln(z) = ln((re)^(i(theta)) = ln(r) + i(theta) = ln(sqrt(a^2+b^2)) + i*arctan(b/a) ln(z) =
   * .5*ln(a^2+b^2) + i*arctan(b/a)
   */

  /**
   * Evaluates complex logarithms.
   * 
   * @param op ComplexNumber
   * @return ComplexNumber post logarithm evaluation.
   */
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
    Double real = hold.getReal();
    Double img = hold.getImaginary();
    Double newReal = (1.0 / 2.0) * Math.log((real * real) + (img * img));
    Double newImg = Math.atan(img / real);
    answer = new ComplexNumber(newReal, newImg);

    return answer;

  }
}
