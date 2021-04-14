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
    ComplexNumber op1;
<<<<<<< HEAD
    
    if (op[0] != null && op.length == 1) 
=======
    ComplexNumber op2;
    if (op != null && op.length == 1) 
>>>>>>> ad819570b421d792ef39a299f6af2509a933f5d1
    {
      op1 = op[0];
    } else 
    {
      return new ComplexNumber(Double.NaN, Double.NaN);
    }
    
    if (op1 == null)
    {
      // TODO: Raise some error
      return null;
    }

<<<<<<< HEAD
    double tempReal = 0;
    double tempImag = 0;
    
    if (op1.getReal() == 0 && op1.getImaginary() == 0)
    {
      return new ComplexNumber(0.0, 0.0);
    }
    
    tempReal = (Math.sqrt((complexAbs(op1) + op1.getReal()) / 2));
    tempImag = op1.getImaginary() / Math.abs(op1.getImaginary()) * (Math.sqrt((complexAbs(op1) - op1.getReal()) / 2));
    
    return new ComplexNumber(tempReal, tempImag);
  }
  
  private double complexAbs(ComplexNumber c)
  {
    double result = 0;
    result = Math.sqrt((c.getReal() * c.getReal()) + (c.getImaginary() * c.getImaginary()));
    return result;
=======
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
>>>>>>> ad819570b421d792ef39a299f6af2509a933f5d1
  }
}

