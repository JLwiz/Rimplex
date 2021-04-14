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
      op1 = op[0];
    } else 
    {
      return answer;
    }
    
    if (op1 == null)
    {
      // TODO: Raise some error
      return null;
    }

    Double op1real = op1.getReal();
    return new ComplexNumber(Math.sqrt(op1real), 0.0);
  }

}
