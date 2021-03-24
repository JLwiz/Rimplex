package util;
import calculations.ComplexNumber;
/**
 * Utility class that validates and parses input for the ComplexNumber calculator.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @author Storm Behrens
 * @version 03/24/2021
 */

public class InputParser
{
  private static InputParser parser = new InputParser();
  private final String validCharacters = "0123456789.+-i";

  /**
   * Default Constructor for InputParser.
   */

  private InputParser()
  {
  }

  /**
   * returns the instance of the parser.
   * 
   * @return InputParser - the parser for the program
   */

  public static InputParser getInstance()
  {
    return parser;
  }

  /**
   * parses an input and returns the resulting ComplexNumber.
   * 
   * @param input
   *          - the input to parse
   * @return ComplexNumber - the resulting complex number
   * @throws NumberFormatException
   *           - if the input is invalid
   */

  public ComplexNumber parseInput(final String input) throws NumberFormatException
  {
    if (input == null || input.length() == 0 || !validInput(input))
    {
      throw new NumberFormatException();
    }
    Double realNumber = 0.0;
    Double imaginaryNumber = 0.0;
    String[] splitInput = input.split("[-+]");
    ComplexNumber number;
    if (splitInput.length <= 3)
    {
      if (splitInput.length == 3)
      {
        realNumber = Double.parseDouble("-" + splitInput[1]);
        imaginaryNumber = Double
            .parseDouble(splitInput[2].substring(0, splitInput[2].length() - 1));
      }
      if (splitInput.length == 2)
      {
        realNumber = Double.parseDouble(splitInput[0]);
        imaginaryNumber = Double
            .parseDouble(splitInput[1].substring(0, splitInput[2].length() - 1));
      }
      if (splitInput.length == 1)
      {
        if (input.contains("i"))
        {
          imaginaryNumber = Double.parseDouble(input.substring(0, input.length() - 1));
        }
        else
        {
          realNumber = Double.parseDouble(input);
        }
      }
    }
    else
    {
      throw new NumberFormatException();
    }
    number = new ComplexNumber(realNumber, imaginaryNumber);
    return number;

  }

  /**
   * Checks to make sure only valid characters are in the input.
   * 
   * @param input
   *          - the string to check
   * @return boolean - whether the input is valid
   */

  private boolean validInput(final String input)
  {
    boolean isValid = true;
    char[] inputChars = input.toCharArray();
    for (char i : inputChars)
    {
      if (validCharacters.lastIndexOf(i) == -1)
      {
        isValid = false;
      }
    }
    return isValid;
  }
}
