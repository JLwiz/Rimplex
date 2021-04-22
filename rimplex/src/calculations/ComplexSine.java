package calculations;

public class ComplexSine implements Operations {

  @Override
  public ComplexNumber calculate(ComplexNumber... op) {
    ComplexNumber answer = new ComplexNumber(0.0, 0.0);
    ComplexNumber hold = answer;
    if (op != null && op.length == 1) 
    {
      if (op[0] != null) 
      {
        hold = op[0];
      }
    }
    if (hold.getReal() == 0 && hold.getImaginary() == 0) 
    {
      throw new NumberFormatException();
    }
    Double real = hold.getReal();
    Double img = hold.getImaginary();
    Double newReal = Math.sin(real) * Math.cosh(img);
    Double newImg = Math.cos(real) * Math.sinh(img);
    answer = new ComplexNumber(newReal, newImg);
    
    return answer;
  }

}
