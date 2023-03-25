import org.example.equation.Equation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquationTests {
    @Test
    public void example() {
        assertEquals(true, new Equation("2*x+5=17").isViable());
        assertEquals(true, new Equation("-1.3*5/x=1.2").isViable());
        //no equals sign
        assertEquals(false, new Equation("12x-15").isViable());
    }

    @Test
    public void nullEmpty() {
        assertEquals(false, new Equation("").isViable());
        assertEquals(false, new Equation(null).isViable());
    }

    @Test
    public void bracketsBalance() {
        assertEquals(false, new Equation(")2*x+5=19").isViable());
        assertEquals(true, new Equation("2*(x+5)=17()").isViable());
        assertEquals(true, new Equation("((x+4)*5)-1=8").isViable());
        assertEquals(false, new Equation(")(x+4=3").isViable());

    }

    @Test
    public void twoOperations() {
        assertEquals(false, new Equation("2/*4x=4").isViable());
        assertEquals(false, new Equation("24/x-*=8").isViable());
        assertEquals(true, new Equation("1*-8x=1").isViable());
        assertEquals(true, new Equation("1*+8x=1").isViable());
    }
}
