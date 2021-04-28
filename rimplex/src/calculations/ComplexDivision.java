package calculations;

import java.math.BigDecimal;

/**
 * ComplexNumber division class.
 * 
 * @author Kenneth Painter
 * @version 03.24.2021
 */
public class ComplexDivision implements Operations
{
//  /**
//   * Does ComplexNumber division.
//   * 
//   * @param op1
//   *          ComplexNumber1
//   * @param op2
//   *          ComplexNumber2
//   * @return ComplexNumber post division.
//   */
//  @Override
//  public ComplexNumber calculate(final ComplexNumber op1, final ComplexNumber op2)
//  {
//    if (// op1 == null || Probably shouldn't let null numerators go through, 
//    but will have to do for now.
//        op2 == null || (op2.getReal() == 0.0 && op2.getImaginary() == 0.0))
//    {
//      // TODO: Raise ArithmeticException
//      // This is a stupid way to handle a 0 denominator. Discuss with team.
//      return new ComplexNumber(0.0, 0.0);
//    }
//
//    Double op1Real = 0.0;
//    Double op1Imag = 0.0;
//
//    if (op1 != null)
//    {
//      op1Real = op1.getReal();
//      op1Imag = op1.getImaginary();
//    }
//
//    Double op2Real = op2.getReal();
//    Double op2Imag = op2.getImaginary();
//
//    Double resultReal = (op1Real * op2Real + op1Imag * op2Imag)
//        / (op2Real * op2Real + op2Imag * op2Imag);
//    Double resultImag = (op1Imag * op2Real - op1Real * op2Imag)
//        / (op2Real * op2Real + op2Imag * op2Imag);
//
//    return new ComplexNumber(resultReal, resultImag);
//  }

  /**
   * Calculates the divisor between two complex numbers.
   * 
   * @return the divisor between two complex numbers
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
    if (// op1 == null || Probably shouldn't let null numerators go through, but will have to do for
        // now.
        op2 == null || (op2.getReal() == 0.0 && op2.getImaginary() == 0.0))
    {
      // TODO: Raise ArithmeticException
      // This is a stupid way to handle a 0 denominator. Discuss with team.
      return new ComplexNumber(0.0, 0.0);
    }

    Double op1Real = 0.0;
    Double op1Imag = 0.0;

    if (op1 != null)
    {
      op1Real = op1.getReal();
      op1Imag = op1.getImaginary();
    }

    Double op2Real = op2.getReal();
    Double op2Imag = op2.getImaginary();
    
    Double resultReal = (op1Real * op2Real + op1Imag * op2Imag) 
        / (op2Real * op2Real + op2Imag * op2Imag);
    
    Double resultImag = (op1Imag * op2Real - op1Real * op2Imag)
        / (op2Real * op2Real + op2Imag * op2Imag);

    return new ComplexNumber(resultReal, resultImag);

  }
}
