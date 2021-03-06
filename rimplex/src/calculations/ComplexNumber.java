package calculations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * A Object class that stores the values associated with a complex number and contains methods to
 * get those values.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @author Storm Behrens, Benjamin Huber, Storm Behrens
 * @version 04/27/2021
 */

public class ComplexNumber
{
  private final Double realNumber;
  private Double imaginaryNumber;
  private DecimalFormat standardFormat;

  /**
   * Default Constructor for Complex Number.
   * 
   * @param realNumber
   *          (Scalar) - the real number value of the complex number
   * @param imaginaryNumber
   *          (vector) - the imaginary number value of the complex number
   */

  public ComplexNumber(final Double realNumber, final Double imaginaryNumber)
  {
    if (realNumber == null)
    {
      this.realNumber = 0.0;
    }
    else
    {
      this.realNumber = realNumber;
    }
    if (imaginaryNumber == null)
    {
      this.imaginaryNumber = 0.0;
    }
    else
    {
      this.imaginaryNumber = imaginaryNumber;
    }
    standardFormat = new DecimalFormat("#.#######");
  }

  /**
   * Gets the conjugate of a complex number.
   * 
   * @return ComplxNumber conjugate;
   */
  public ComplexNumber conjugate()
  {
    return new ComplexNumber(realNumber, imaginaryNumber * -1.0);
  }

  /**
   * Gets the inverse of a complex number.
   * 
   * @return ComplxNumber conjugate;
   */
  public ComplexNumber inverse()
  {
    ComplexMultiplication multi = new ComplexMultiplication();
    ComplexDivision div = new ComplexDivision();
    ComplexNumber one = new ComplexNumber(1.0, 0.0);
    // 1 / z
    ComplexNumber[] zPre = new ComplexNumber[] {one, this};
    // conj(z) / conj(z)
    ComplexNumber[] zRat = new ComplexNumber[] {conjugate(), conjugate()};
    ComplexNumber z = div.calculate(zPre);
    ComplexNumber rat = div.calculate(zRat);
    ComplexNumber back = multi.calculate(new ComplexNumber[] {z, rat});
    return back;
  }

  /**
   * get method for the real number.
   * 
   * @return Double - the real number
   */

  public Double getReal()
  {
    return realNumber;
  }

  /**
   * get method for the imaginary number.
   * 
   * @return Double - the imaginary number
   */

  public Double getImaginary()
  {
    return imaginaryNumber;
  }

  /**
   * converts number into its fractional forms.
   * 
   * @param number
   *          - the number to convert into a fraction
   * @return String - the number in its fractional form
   */

  private String getFraction(final Double number)
  {
    // requires special checks for numbers the represent 1/3 or 2/3.
    boolean onlySix = true;
    boolean onlyThree = true;
    String baseThree = "3.0";

    String[] numParts = number.toString().split("\\.");
    for (int i = 0; i < numParts[1].length(); i++)
    {
      if (numParts[1].charAt(i) != '3' || numParts[1].length() < 3)
      {
        onlyThree = false;
      }
      if (numParts[1].charAt(i) != '6' || numParts[1].length() < 3)
      {
        onlySix = false;
      }
    }
    BigDecimal den; // denominator
    BigDecimal num; // numerator
    if (onlyThree)
    {
      den = new BigDecimal(baseThree);
      num = (new BigDecimal(numParts[0]).multiply(den)).add(new BigDecimal("1.0"));
    }
    else if (onlySix)
    {
      den = new BigDecimal(baseThree);
      num = (new BigDecimal(numParts[0]).multiply(den)).add(new BigDecimal("2.0"));
    }
    else
    {
      den = BigDecimal.TEN.pow(numParts[1].length());
      num = (new BigDecimal(numParts[0]).multiply(den)).add(new BigDecimal(numParts[1]));
    }
    if (number < 0 && num.intValue() >= 0)
    {// checks to make sure negative is applied properly. negative can be lost with numbers < 1 as
     // the -0 is lost in the split
      num = num.negate();
    }
    return reduceFraction(num.intValue(), den.intValue());

  }

  /**
   * returns the fraction version of a Complex number in the (realNumber+/-imaginaryNumberi) format.
   * 
   * @return String - the completed complex number in fraction format
   */

  public String toFraction()
  {

    String realFraction = "";
    String imaginaryFraction = "";

    realFraction = standardFormat.format(realNumber.doubleValue());
    imaginaryFraction = standardFormat.format(imaginaryNumber.doubleValue());

    realFraction = getFraction(Double.valueOf(realFraction));
    imaginaryFraction = getFraction(Double.valueOf(imaginaryFraction));

    return toStringHelper(realFraction, imaginaryFraction);
  }
  
  /**
   * Returns the polar form of a complex number r(cos(@)+isin(@)).
   * @return String polar form
   */
  public String toPolar()
  {
    double theta = Math.atan(imaginaryNumber/realNumber);
    double r = Math.sqrt((realNumber * realNumber) + (imaginaryNumber * imaginaryNumber));
    String str = String.format("%.6f(cos(%.6f)+isin(%.6f))", r, theta, theta);
    return str;
  }

  /**
   * Reduces the fraction of a real or imaginary number.
   * 
   * @param num
   *          - the numweator for the number
   * @param den
   *          - the denominator for the nmber
   * @return String - the reduced fraction
   */
  private String reduceFraction(final int num, final int den)
  {
    String number = "";
    int greatestCommonDem = BigInteger.valueOf(num).gcd(BigInteger.valueOf(den)).intValue();
    // greatest common denominator
    int[] reducedFraction = {num / greatestCommonDem, den / greatestCommonDem};
    if (num == 0)// prevents 0/1
    {
      number = "0";
    }
    if (reducedFraction[1] == 1)
    {
      number = String.valueOf(reducedFraction[0]);
    }
    else
    {
      number = reducedFraction[0] + "/" + reducedFraction[1];
    }
    return number;
  }

  /**
   * toString method for complex numbers. returns in format (realNumber+/-imaginaryNumberi) where
   * the real and imaginary numbers are doubles and i is added to the end of the imaginary number.
   * 
   * @return String - the text for the complex number
   */
  @Override
  public String toString()
  {

    // Initializing our "String Components".
    String realComponent = "";
    String imaginaryComponent = "";

    // Our handy DecimalFormat object does all the hard work for us
    realComponent += standardFormat.format(realNumber.doubleValue());
    imaginaryComponent += standardFormat.format(imaginaryNumber.doubleValue());
    if (realComponent.indexOf('.') + 10 == realComponent.length())
    {
      realComponent = realComponent.substring(0, realComponent.length() - 1);
    }
    return toStringHelper(realComponent, imaginaryComponent);
  }

  /**
   * returns the raw complex Number (no decimal formating) String for passing to other functions in
   * the calculator.
   * 
   * @return String - the raw Complex number
   */

  public String getRawString()
  {
    String eNum = "E";
    DecimalFormat df = new DecimalFormat("#");
    String imaginaryString = imaginaryNumber.toString();
    String realString = realNumber.toString();
    if (imaginaryString.contains(eNum))
    {
      int index = imaginaryNumber.toString().indexOf(eNum, 0) + 1;
      int newLength = Integer.parseInt(imaginaryString.substring(index)) + 1;
      df.setMaximumFractionDigits(newLength);
      standardFormat = df;
      imaginaryString = df.format(imaginaryNumber);
    }

    if (realString.contains(eNum))
    {
      int index = realString.indexOf(eNum, 0) + 1;
      String sub = realString.substring(index);
      int newLength = Integer.parseInt(sub) + 1;
      df.setMaximumFractionDigits(newLength);
      standardFormat = df;
      realString = df.format(realNumber);
    }
    return toStringHelper(realString, imaginaryString);
  }
  
  /**
   * keeps consistent format between complex numbers in fraction and decimal format.
   * 
   * @param real
   *          - the real number
   * @param imaginary
   *          - the imaginary number
   * @return the completed complex number
   */

  public String toStringHelper(final String real, final String imaginary)
  {
    String start = "(";
    String operator = "";
    String end = "i)";

    // Checking if our imaginary component is not negative to assign our operator
    if (imaginaryNumber >= 0.0)
    {
      operator += "+";

    }
    String nan = "NaN";
    String back = start + real + operator + imaginary + end;
    if (real.equals(nan) || imaginary.equals(nan))
    {
      back = "Undefined";
    } 
    else if (realNumber == 0 && imaginaryNumber == 0)
    {
      back = start + real + operator + imaginary + end;
    }
    else if (imaginaryNumber == 0)
    {
      back = start + real + end.substring(1);
    }
    else if (realNumber == 0)
    {
      back = start + imaginary + end;
    }

    return back;
  }
}
