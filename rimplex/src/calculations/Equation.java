package calculations;

/**
 * Holds complex numbers, operator, and will solve the equation
 * using the correct operation.
 * 
 * @author Andrew Fryer
 * @version 1.0 (03/23/2021)
 */
public class Equation
{

  //----------Declarations----------
  private static Equation single_instance = null;
  
  private ComplexNumber firstOp;
  private ComplexNumber secondOp;
  
  private String operator;
  
  
  //----------Constructor----------
  /**
   * Default Constructor.
   */
  public Equation()
  {} // Default Constructor.
  
  //----------Singleton----------
  
  /**
   * getInstance - Singleton
   */
  public static Equation getInstance()
  {
    if (single_instance == null) single_instance = new Equation();
    return single_instance;
  } // getInstance method.
  
  //----------Public Methods----------
  
  /**
   * setFirstOp - Will set the first operand.
   * 
   * @param op (ComplexNumber)
   */
  public void setFirstOp(ComplexNumber op)
  {
    this.firstOp = op;
  } // setFirstOp method.
  
  /**
   * setOperator - Will set the operator.
   * 
   * @param op (String)
   */
  public void setOperator(String op)
  {
    this.operator = op;
  }
  
  /**
   * setSecondOp - Will set the second operand.
   * 
   * @param op (ComplexNumber)
   */
  public void setSecondOp(ComplexNumber op)
  {
    this.secondOp = op;
  } // setSecondOp method.
  
  /**
   * solve - Will solve the equation.
   */
  public ComplexNumber solve()
  {
    ComplexNumber result = null;
    
    switch(operator)
    {
      case "+":
        //ComplexAddtion.calculate(firstOp, secondOp);
        break;
      case "-":
        //ComplexSubtraction.calculate(firstOp, secondOp);
        break;
      case "*":
        //ComplexMultiplication.calculate(firstOp, secondOp);
        break;
      case "÷":
        //ComplexDivision.calculate(firstOp, secondOp);
        break;
      default:
        break;
    }
    
    this.setFirstOp(result);
    return result;
    
  } // solve method.
  
} // Equation class.
