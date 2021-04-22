package calculations;

public class ComplexTangent implements Operations {

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


    // can't take log of zero, so an exception needs to be raised.
    if (hold.getReal() == 0 && hold.getImaginary() == 0) 
    {
      throw new NumberFormatException();
    }
    Operations sine = new ComplexSine();
    ComplexNumber sin = sine.calculate(hold);
    Operations cosine = new ComplexCosine();
    ComplexNumber cos = cosine.calculate(hold);
    Operations divide = new ComplexDivision();
    ComplexNumber tangent = divide.calculate(sin, cos);
    Double newReal = tangent.getReal();
    Double newImg = tangent.getImaginary();
    answer = new ComplexNumber(newReal, newImg);

    return answer;
  }

}
