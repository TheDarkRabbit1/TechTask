import org.example.EquationHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquationHandlerTests {
    @Test
    public void example() {
        assertEquals(true, new EquationHandler("2*x+5=17").isViable());
        assertEquals(true, new EquationHandler("-1.3*5/x=1.2").isViable());
        //no equals sign
        assertEquals(false, new EquationHandler("12x-15").isViable());
    }

    @Test
    public void nullEmpty() {
        assertEquals(false, new EquationHandler("").isViable());
        assertEquals(false, new EquationHandler(null).isViable());
    }

    @Test
    public void bracketsBalance() {
        assertEquals(false, new EquationHandler(")2*x+5=19").isViable());
        assertEquals(true, new EquationHandler("2*(x+5)=17()").isViable());
    }

    @Test
    public void twoOperations() {
        assertEquals(false, new EquationHandler("2/*4x=4").isViable());
        assertEquals(false, new EquationHandler("24/x-*=8").isViable());
        assertEquals(true, new EquationHandler("1*-8x=1").isViable());
        assertEquals(true, new EquationHandler("1*+8x=1").isViable());
    }
}
