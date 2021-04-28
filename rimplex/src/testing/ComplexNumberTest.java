package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;

import calculations.ComplexNumber;
import org.junit.jupiter.api.Test;

class ComplexNumberTest
{
  
  ComplexNumber alpha = new ComplexNumber(Double.valueOf(3), Double.valueOf(4));
  ComplexNumber beta = new ComplexNumber(Double.valueOf(-4), Double.valueOf(3));
  ComplexNumber charlie = new ComplexNumber(null, null);
  ComplexNumber delta = new ComplexNumber(Double.valueOf(7), Double.valueOf(-2));
  ComplexNumber echo = new ComplexNumber(Double.valueOf(11.0001), Double.valueOf(0.00001));
  
  @Test
  void ComplexNumberGetRealTest()
  {
    assertEquals(3, alpha.getReal().doubleValue());
    assertEquals(-4, beta.getReal().doubleValue());
    assertEquals(0, charlie.getReal().doubleValue());
    assertEquals(7, delta.getReal().doubleValue());
  }
  
  @Test
  void ComplexNumberGetImaginaryTest()
  {
    assertEquals(4, alpha.getImaginary().doubleValue());
    assertEquals(3, beta.getImaginary().doubleValue());
    assertEquals(0, charlie.getImaginary().doubleValue());
    assertEquals(-2, delta.getImaginary().doubleValue());
  }
  
  @Test
  void ComplexNumberToStringTest()
  {
    assertEquals("(3+4i)", alpha.toString());
    assertEquals("(-4+3i)", beta.toString());
    assertEquals("(0+0i)", charlie.toString());
    assertEquals("(7-2i)", delta.toString());
    assertEquals("(11.0001+0.00001i)", echo.toString());
  }
  
  @Test
  void inverseTest() 
  {
    ComplexNumber op = new ComplexNumber(2.0, 3.0);
    ComplexNumber actual = op.inverse();
    ComplexNumber expected = new ComplexNumber(2.0/13.0, -3.0/13.0);
    assertEquals(actual.getReal(), expected.getReal(), 0.001);
  }
  
  @Test // TODO COME UP WITH A WAY TO RANDOM TEST OUR TOSTRING
  void ComplexNumberRandomTests()
  {
    for (int i = 0; i < 100; i++) {
      double real = Math.random() * 200 - 100;
      double imaginary = Math.random() * 200 - 100;
      ComplexNumber tested = new ComplexNumber(Double.valueOf(real), Double.valueOf(imaginary));
      assertEquals(real, tested.getReal());
      assertEquals(imaginary, tested.getImaginary());
    }
  }
  
  @Test
  void ComplexFractionTest()
  {
    ComplexNumber number = new ComplexNumber(3.33, 6.66);
    assertEquals("(333/100+333/50i)", number.toFraction());
    
    number = new ComplexNumber(7.7584, 8.9393);
    assertEquals("(4849/625+89393/10000i)", number.toFraction());

    number = new ComplexNumber(5.3311, 4.6613);
    assertEquals("(53311/10000+46613/10000i)", number.toFraction());

    number = new ComplexNumber(3.33333, 6.66666);
    assertEquals("(10/3+20/3i)", number.toFraction());

    number = new ComplexNumber(-0.333, -0.666666);
    assertEquals("(-1/3-2/3i)", number.toFraction());
  }
  
  @Test
  void ComplexPolarTest()
  {
   ComplexNumber number = new ComplexNumber(3.33, 5.1);
   assertEquals("6.090887(cos(0.992356)+isin(0.992356))", number.toPolar());
   
   number = new ComplexNumber(5.112, 49.491);
   assertEquals("49.754313(cos(1.467870)+isin(1.467870))", number.toPolar());
  }
  
}
