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
    
    if (op[0] != null && op.length == 1) 
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
  }
}

