import org.example.EquationHandler;
import org.junit.jupiter.api.Test;

public class EquationHandlerTests {
    @Test
    public void example(){
        new EquationHandler("2*x+5=17");
        new EquationHandler("-1.3*5/x=1.2");
    }
    @Test
    public void nullEmpty(){
        new EquationHandler("");
        new EquationHandler(null);
    }
    @Test
    public void bracketsBalance(){
        new EquationHandler(")2*x+5=19");
        new EquationHandler("2*(x+5)=17()");
    }
    @Test
    public void twoOperations(){
        new EquationHandler("2/*4x=4");
        new EquationHandler("24/x-*=8");
        new EquationHandler("1*-8x=1");
        new EquationHandler("1*+8x=1");
    }
}
