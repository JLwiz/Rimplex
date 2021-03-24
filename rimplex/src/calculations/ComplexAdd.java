package calculations;


public class ComplexAdd implements Operations {

  /*
   * 
   */
  @Override
  public ComplexNumber calculate(ComplexNumber op1, ComplexNumber op2) {
    Double realSum = op1.getReal() + op2.getReal();
    Double imgSum = op1.getImaginary() + op2.getImaginary();
    return new ComplexNumber(realSum, imgSum);
  }

}
