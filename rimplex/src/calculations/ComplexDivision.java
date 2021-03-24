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
  public ComplexNumber calculate(ComplexNumber op1, ComplexNumber op2)
  {
    if (op1 == null || op2 == null) {
      // TODO: Raise some error
      return null;
    }
  
    Double op2Real = op2.getReal();
    Double op2Imag = op2.getImaginary();
    
    Double opConjugate = -1 * op2Imag;
    ComplexNumber conjugate = new ComplexNumber(op2Real, opConjugate);
    
    ComplexMultiplication mult = new ComplexMultiplication();
    return mult.calculate(op1, conjugate);
  }

}
