package calculations;

/**
 * ComplexNumber SquareRoot class.
 * 
 * @author Kenneth Painter, Storm Behrens
 * @version 04.14.2021
 */
public class ComplexSquareRoot implements Operations
{
  @Override
  public ComplexNumber calculate(final ComplexNumber... op)
  {
    ComplexExponentiation exp = new ComplexExponentiation();
    ComplexNumber answer = new ComplexNumber(0.0, 0.0);
    ComplexNumber op1;
    ComplexNumber op2;
    if (op != null && op.length == 1)
    {
      op1 = op[0];
    }
    else
    {
      return answer;
    }

    if (op1 == null)
    {
      // TODO: Raise some error
      return null;
    }

    Double real = op1.getReal();
    Double imag = op1.getImaginary();
    double x = 0;
    double y = 0;
    if (imag != 0)
    {
      double r = Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
      double theta = (Math.atan2(imag, real));
      r = Math.sqrt(r);
      x = Math.cos((theta + (2 * Math.PI * 0)) / 2) * r;
      y = Math.sin((theta + (2 * Math.PI * 0)) / 2) * r;
    }
    else
    {
      x = Math.sqrt(real);
    }
    return new ComplexNumber(x, y);
  }

}
