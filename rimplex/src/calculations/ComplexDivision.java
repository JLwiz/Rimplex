package calculations;

/**
 * ComplexNumber division class.
 * 
 * @author Kenneth Painter
 * @version 03.24.2021
 */
public class ComplexDivision implements Operations
{
  /** Does ComplexNumber division.
  * 
  * @param op1
  *          ComplexNumber1
  * @param op2
  *          ComplexNumber2
  * @return ComplexNumber post division.
  */
  @Override
  public ComplexNumber calculate(final ComplexNumber op1, final ComplexNumber op2)
  {
    if (//op1 == null ||
        op2 == null ||
       (op2.getReal() == 0.0 && op2.getImaginary() == 0.0)) 
    {
      // TODO: Raise some error
      // This is a stupid way to handle a 0 denominator. Discuss with team.
      return new ComplexNumber(0.0, 0.0);
    }
    
    Double op1Real = 0.0;
    Double op1Imag = 0.0;
    
    if (op1 != null) {
        op1Real = op1.getReal();
        op1Imag = op1.getImaginary();
    }

    Double op2Real = op2.getReal();
    Double op2Imag = op2.getImaginary();
    
    Double resultReal = (op1Real * op2Real + op1Imag * op2Imag) / 
                        (op2Real * op2Real + op2Imag * op2Imag);
    Double resultImag = (op1Imag * op2Real - op1Real * op2Imag) /
                        (op2Real * op2Real + op2Imag * op2Imag);
    
    resultReal *= -1;
    resultImag *= -1;
    
    return new ComplexNumber(resultReal, resultImag);
  }
}
