package calculations;

/**
 * ComplexNumber Exponentiation class.
 * 
 * @author Kenneth Painter
 * @version 03.24.2021
 */
public class ComplexExponentiation implements Operations
{
  @Override
  public ComplexNumber calculate(final ComplexNumber... op) 
  {
    ComplexNumber answer = new ComplexNumber(0.0, 0.0);
    ComplexNumber op1;
    ComplexNumber op2;
    if (op != null && op.length == 2) 
    {
      op1 = op[0];
      op2 = op[1];
    } else 
    {
      return answer;
    }
    
    answer = op[0];
    ComplexMultiplication mult = new ComplexMultiplication();
    
    if (op1 == null || op2 == null)
    {
      return new ComplexNumber(Double.NaN, Double.NaN);
    }
    
    for (int i = 1; i < op[1].getReal(); i++)
    {

      answer = mult.calculate(answer, op[0]);
    }
    

    return answer;
  }
}
