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
  } // Default Constructor.

  // ----------Singleton----------

  /**
   * getInstance - Singleton
   */
  public static Equation getInstance()
  {
    if (single_instance == null)
      single_instance = new Equation();
    return single_instance;
  } // getInstance method.

  // ----------Public Methods----------

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
    switch (op)
    {
      case "+":
        operator = new ComplexAddition();
        break;
      case "-":
        operator = new ComplexSubtraction();
        break;
      case "*":
        operator = new ComplexMultiplication();
        break;
      case "�":
        operator = new ComplexDivision();
        break;
      default:
        break;
    }
  }

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
    if (operator == null)
    {
      return new ComplexNumber(0.0, 0.0);
    }
    return operator.calculate(firstOp, secondOp);
  } // solve method.

} // Equation class.
