package calculations;

/**
 * ComplexNumber subtraction class.
 * 
 * @author Kenneth Painter
 * @version 03.24.2021
 */
public class ComplexSubtraction implements Operations
{
//  /**
//   * Does ComplexNumber subtraction.
//   * 
//   * @param op1
//   *          ComplexNumber1
//   * @param op2
//   *          ComplexNumber2
//   * @return ComplexNumber post subtraction.
//   */
//  @Override
//  public ComplexNumber calculate(final ComplexNumber op1, final ComplexNumber op2)
//  {
//    if (op1 == null || op2 == null)
//    {
//      // TODO: Raise some error
//      return null;
//    }
//
//    Double op1real = op1.getReal();
//    Double op1imag = op1.getImaginary();
//
//    Double op2real = op2.getReal();
//    Double op2imag = op2.getImaginary();
//
//    return new ComplexNumber(op1real - op2real, op1imag - op2imag);
//  }

  
  /**
   * Calculates the result of a subtraction method between two complex numbers.
   * 
   * @return the result of the subtraction.
   */
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
    if (op1 == null || op2 == null)
    {
      // TODO: Raise some error
      return null;
    }

    Double op1real = op1.getReal();
    Double op1imag = op1.getImaginary();

    Double op2real = op2.getReal();
    Double op2imag = op2.getImaginary();

    return new ComplexNumber(op1real - op2real, op1imag - op2imag);
  }

}
