package calculations;

/**
 * ComplexNumber SquareRoot class.
 * 
 * @author Kenneth Painter
 * @version 03.24.2021
 */
public class ComplexSquareRoot implements Operations
{
  @Override
  public ComplexNumber calculate(final ComplexNumber... op) 
  {
    ComplexNumber answer = new ComplexNumber(0.0, 0.0);
    ComplexNumber op1;
    ComplexNumber op2;
    if (op != null && op.length == 1) 
    {
      // unitary operator
      op1 = op[0];
//      op2 = op[1];
    } else 
    {
      return answer;
    }
    
    if (op1 == null)
    {
      // TODO: Raise some error
      return null;
    }

    Double real = op1.getReal();
    Double realAbs = Math.abs(real);
    Double img = op1.getImaginary();
    Double imgAbs = Math.abs(img);
    Double sqrtReal = Math.sqrt(realAbs);
    Double sqrtImg = Math.sqrt(imgAbs);
    Double temp = 0.0;
    Double temp2 = 0.0;
    
    // This establishes the imaginary scalar in the variable temp2.
    if (realAbs >= imgAbs)
    {
      temp = imgAbs/realAbs;
      Double otherSqrt = Math.sqrt(.5*(1.0 + Math.sqrt(1.0 + temp*temp)));
      temp2 = sqrtReal * otherSqrt;
    } else 
    {
      temp = realAbs/imgAbs;
      Double otherSqrt = Math.sqrt(.5*(temp + Math.sqrt(1.0 + temp*temp)));
      temp2 = sqrtImg * otherSqrt;
    }
    
    // After getting the final imaginary you can find the real vector.
    Double newImg = 0.0;
    Double newReal = 0.0;
    if (real >= 0) 
    {
      newReal = temp2;
      newImg = img / (2.0 * temp2);
    } else if (img > 0.0) 
    {
      newImg = temp2;
      newReal = img / (2.0 * newImg);
    } else
    { 
      newImg = temp2 * -1;
      newReal = img / (2.0 * newImg);
    }
    
    return new ComplexNumber(newReal, newImg);
  }

}
