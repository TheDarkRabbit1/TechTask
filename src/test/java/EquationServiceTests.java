import org.example.equation.Equation;
import org.example.equation.EquationRepository;
import org.example.equation.EquationService;
import org.junit.jupiter.api.Test;
import org.matheclipse.parser.client.eval.DoubleEvaluator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EquationServiceTests {
    private EquationService equationService = new EquationService();
    private EquationRepository equationRepository = new EquationRepository();
    @Test
    public void testGetEquationById() {
        Equation equation = equationService.getEquation(1L);
        assertNotNull(equation);
        assertEquals("x^2=4", equation.getBody());
        assertEquals(1L, equation.getId());
    }

    @Test
    public void testGetEquationByBody() {
        Equation equation = equationService.getEquationByBody("x^2=4");
        assertNotNull(equation);
        assertEquals("x^2=4", equation.getBody());
        assertEquals(1L, equation.getId());
    }

    @Test
    public void testAddEquation() {
        Equation equation = new Equation("2x=4");
        equationService.addEquation(equation);
        assertEquals(equation, equationService.getEquationByBody("2x=4"));
    }

    @Test
    public void testAddRootsForEquation() {
        Equation equation = new Equation("x^2=4");
        equationService.addEquation(equation);

        equationService.addRootsForEquation(equation, Arrays.asList(2.0f, -2.0f));
        assertEquals(2, equationRepository.getRootsForEquation(equation.getId()).size());
        assertTrue(equationRepository.getRootsForEquation(equation.getId()).contains(2.0f));
        assertTrue(equationRepository.getRootsForEquation(equation.getId()).contains(-2.0f));
    }

    @Test
    public void testIsRootFitsForEquation() {
        Equation equation = new Equation("x^2=4");
        assertTrue(equationService.isRootFitsForEquation(equation, 2.0f));
        assertTrue(equationService.isRootFitsForEquation(equation, -2.0f));
        assertFalse(equationService.isRootFitsForEquation(equation, 3.0f));
        assertFalse(equationService.isRootFitsForEquation(equation, -3.0f));
    }

    @Test
    public void testSearchByRoot() {
        List<Equation> equations = equationService.searchByRoot(2.0f);
        assertNotNull(equations);
        assertEquals(1, equations.size());
        assertEquals("x^2=4", equations.get(0).getBody());
    }

    @Test
    public void testOneRootOnlyEquations() {
        List<Equation> equations = equationService.oneRootOnlyEquations();
        assertNotNull(equations);
        assertEquals(2, equations.size());
        assertEquals("2x=4", equations.get(0).getBody());
        assertEquals("x^2=4", equations.get(1).getBody());
    }

    @Test
    public void testGetEquations() {
        List<Equation> equations = equationService.getEqations();
        assertNotNull(equations);
        assertEquals(3, equations.size());
    }
}
