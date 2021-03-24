package calculations;

public class ComplexDivision implements Operations
{

  @Override
  public ComplexNumber calculate(ComplexNumber op1, ComplexNumber op2)
  {
    if (op1 == null || op2 == null) {
      // TODO: Raise some error
      return null;
    }
    
    Double op1real = op1.getReal();
    Double op1imag = op1.getImaginary();
    
    Double op2real = op2.getReal();
    Double op2imag = op2.getImaginary();
    
    return null;
  }

}
