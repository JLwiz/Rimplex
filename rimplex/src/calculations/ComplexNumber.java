package calculations;

import java.text.DecimalFormat;

/**
 * A Object class that stores the values associated with a complex number and contains methods to
 * get those values.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @author Storm Behrens, Benjamin Huber
 * @version 03/24/2021
 */

public class ComplexNumber
{
  private final Double realNumber;
  private final Double imaginaryNumber;

  /**
   * Default Constructor for Complex Number.
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
    // Creating our "format" objects
    DecimalFormat standardFormat = new DecimalFormat("#.#######");
    
    // Initializing our "String Components".
    String start = "(";
    String realComponent = "";
    String operator = "";
    String imaginaryComponent = "";
    String end = "i)";
    
    // Our handy DecimalFormat object does all the hard work for us
    realComponent += standardFormat.format(realNumber.doubleValue());
    imaginaryComponent += standardFormat.format(imaginaryNumber.doubleValue());
    
    // Checking if our imaginary component is not negative to assign our operator
    if (imaginaryNumber >= 0)
    {
      operator += "+";
    }
    
    return start + realComponent + operator + imaginaryComponent + end;
  }
}
