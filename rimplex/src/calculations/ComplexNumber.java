package calculations;

/**
 * A Object class that stores the values associated with a complex number and contains methods to
 * get those values.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @author Storm Behrens
 * @version 03/24/2021
 */

public class ComplexNumber
{
  private final Double realNumber;
  private final Double imaginaryNumber;

  /**
   * Default Constructor for Complex Number
   * 
   * @param realNumber
   *          - the real number value of the complex number
   * @param imaginaryNumber
   *          - the imaginary number value of the complex number
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
   * toString method for complex numbers. returns in format (realNumber+/-imaginaryNumberi) where
   * the real and imaginary numbers are doubles and i is added to the end of the imaginary number.
   * 
   * @return String - the text for the complex number
   */

  @Override
  public String toString()
  {
    String complexString = "";
    String complexEnd = "i)";
    complexString = "(" + realNumber;
    if (imaginaryNumber < 0)
    {
      complexString += imaginaryNumber + complexEnd;
    }
    else
    {
      complexString += "+" + imaginaryNumber + complexEnd;
    }
    return complexString;
  }
}
