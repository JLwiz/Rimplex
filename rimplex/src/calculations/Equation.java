package calculations;

/**
 * Holds complex numbers, operator, and will solve the equation using the correct operation.
 * 
 * @author Andrew Fryer
 * @version 1.0 (03/23/2021)
 */
public class Equation
{

  // ----------Declarations----------
  private static Equation single_instance = null;

  private ComplexNumber firstOp;
  private ComplexNumber secondOp;

  private Operations operator;

  // ----------Constructor----------
  /**
   * Default Constructor.
   */
  public Equation()
  {
    firstOp = null;
    secondOp = null;
    operator = null;
  } // Default Constructor.

  // ----------Singleton----------

  /**
   * getInstance - Singleton.
   * 
   * @return the one and only Equation class.
   */
  public static Equation getInstance()
  {
    if (single_instance == null)
      single_instance = new Equation();
    return single_instance;
  } // getInstance method.

  // ----------Public Methods----------

  /**
   * getFirstOp - returns the first operand.
   * 
   * @return ComplexNumber - the first operand
   */

  public ComplexNumber getFirstOp()
  {
    return firstOp;
  }

  /**
   * operandEmpty - Will return true if the first operand is null, false if it isn't.
   * 
   * @return a boolean representing if the operand is null or not.
   */
  public boolean operandEmpty()
  {
    if (firstOp == null)
      return true;
    return false;
  } // operandEmpty method.

  /**
   * operatorEmpty - Will return true if the first operator is empty, false if it isn't.
   * 
   * @return a boolean representing if the operator is null or not.
   */
  public boolean operatorEmpty()
  {
    if (operator == null)
      return true;
    return false;
  } // operatorEmpty method.

  /**
   * setFirstOp - Will set the first operand.
   * 
   * @param op
   *          (ComplexNumber)
   */
  public void setFirstOp(final ComplexNumber op)
  {
    this.firstOp = op;
  } // setFirstOp method.

  /**
   * setOperator - Will set the operator.
   * 
   * @param op
   *          (String)
   */
  public void setOperator(final String op)
  {
    if (op == null || op.trim().equals(""))
      operator = null;
    else
    {
      switch (op)
      {
        case "+":
          operator = new ComplexAddition();
          break;
        case "-":
          operator = new ComplexSubtraction();
          break;
        case "×":
          operator = new ComplexMultiplication();
          break;
        case "÷":
          operator = new ComplexDivision();
          break;
        case "log":
          operator = new ComplexLogarithm();
          break;
        case "sin":
          operator = new ComplexSine();
          break;
        case "cos":
          operator = new ComplexCosine();
          break;
        case "tan":
          operator = new ComplexTangent();
          break;
        case "sqrt":
          operator = new ComplexSquareRoot();
        default:
          operator = null;
          break;
      }
    }
  } // setOperator method.

  /**
   * setSecondOp - Will set the second operand.
   * 
   * @param op
   *          (ComplexNumber)
   */
  public void setSecondOp(final ComplexNumber op)
  {
    this.secondOp = op;
  } // setSecondOp method.

  /**
   * solve - Will solve the equation.
   * 
   * @return the calculated equation.
   */
  public ComplexNumber solve()
  {

    ComplexNumber result = new ComplexNumber(0.0, 0.0);
    if (operator == null || firstOp == null || secondOp == null)
    {
      if (firstOp != null && secondOp == null
          && (operator instanceof ComplexLogarithm || operator instanceof ComplexSine
              || operator instanceof ComplexCosine || operator instanceof ComplexTangent
              || operator instanceof ComplexSquareRoot))
      {
        result = operator.calculate(firstOp);
      }
      else
      {
        return result;
      }

    }
    else
    {
      result = operator.calculate(firstOp, secondOp);
    }

    setFirstOp(result);
    setSecondOp(null);
    setOperator(null);
    return result;
  } // solve method.

} // Equation class.
