package calculations;

/**
 * Calculates the tangent of a ComplexNumber.
 * 
 * @author Jacob Lewis
 * @version 4/27/2021
 */
public class ComplexTangent implements Operations
{

  /**
   * Calculates the tangent of a ComplexNumber.
   * 
   * @return the result of the calculation.
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
    Double real = hold.getReal();
    Double img = hold.getImaginary();
//    Operations sine = new ComplexSine();
//    ComplexNumber sin = sine.calculate(hold);
//    Operations cosine = new ComplexCosine();
//    ComplexNumber cos = cosine.calculate(hold);
    Double numReal = Math.sin(real) * Math.cosh(img);
    Double numImg = Math.cos(real) * Math.sinh(img);
    ComplexNumber numerator = new ComplexNumber(numReal, numImg);
    Double demReal = Math.cos(real) * Math.cosh(img);
    Double demImg = Math.sin(real) * Math.sinh(img);
    ComplexNumber denominator = new ComplexNumber(demReal, -demImg);
    
    Operations divide = new ComplexDivision();
    ComplexNumber tangent = divide.calculate(numerator, denominator);
    Double newReal = tangent.getReal();
    Double newImg = tangent.getImaginary();
    answer = new ComplexNumber(newReal, newImg);

    return answer;
  }

}
