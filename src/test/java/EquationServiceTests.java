import org.example.equation.Equation;
import org.example.equation.EquationRepository;
import org.example.equation.EquationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EquationServiceTests {
    private EquationService equationService = new EquationService();
    private EquationRepository equationRepository = new EquationRepository();
    @BeforeEach
    public void cleaning(){
        equationRepository.clearTablesForTests();
    }
    @Test
    public void testGetEquationById() {
        Equation equation = new Equation("x^2=4");
        equationService.addEquation(equation);

        Equation equation2 = new Equation("2x=4");
        equationService.addEquation(equation2);

        Equation equation3 = new Equation("3x=4");
        equationService.addEquation(equation3);

        Equation result = equationService.getEquation(equation.getId());
        assertNotNull(result);
        assertEquals(equation.getId(), result.getId());
        assertEquals(equation.getBody(), result.getBody());
    }

    @Test
    public void testGetEquationByBody() {
        Equation equation = new Equation("x^2=4");
        equationService.addEquation(equation);

        Equation equation2 = new Equation("2x=4");
        equationService.addEquation(equation2);

        Equation equation3 = new Equation("3x=4");
        equationService.addEquation(equation3);

        Equation result = equationService.getEquationByBody("x^2=4");
        assertNotNull(result);
        assertEquals(equation.getBody(), result.getBody());
    }

    @Test
    public void testAddEquation() {
        Equation equation = new Equation("2x=4");
        equationService.addEquation(equation);
        Equation result = equationService.getEquationByBody("2x=4");
        assertEquals(equation.getBody(), result.getBody());
    }

    @Test
    public void testAddRootsForEquation() {
        Equation equation = new Equation("x^2=4");
        equationService.addEquation(equation);
        equationService.addRootsForEquation(equation, Arrays.asList(2.0f, -2.0f));

        List<Float> roots = equationRepository.getRootsForEquation(equation.getId());
        assertEquals(2, roots.size());
        assertTrue(roots.contains(2.0f));
        assertTrue(roots.contains(-2.0f));
    }

    @Test
    public void testIsRootFitsForEquation() {
        Equation equation = new Equation("x^2=4");
        equationService.addEquation(equation);

        assertTrue(equationService.isRootFitsForEquation(equation, 2.0f));
        assertTrue(equationService.isRootFitsForEquation(equation, -2.0f));
        assertFalse(equationService.isRootFitsForEquation(equation, 3.0f));
        assertFalse(equationService.isRootFitsForEquation(equation, -3.0f));
    }
    @Test
    public void testOneRootOnlyEquations() {
        Equation equation = new Equation("2x=4");
        equationService.addEquation(equation);
        equationService.addRootsForEquation(equation,List.of(2.0f));

        Equation equation2 = new Equation("x^2=4");
        equationService.addEquation(equation2);
        equationService.addRootsForEquation(equation2,List.of(2.0f,-2.0f));
        List<Equation> equations = equationService.oneRootOnlyEquations();

        assertNotNull(equations);
        assertEquals(1, equations.size());
        assertTrue(equations.contains(equation));
        assertFalse(equations.contains(equation2));
    }
    @Test
    public void testSearchByRoot() {
        Equation equation = new Equation("x^2=4");
        equationService.addEquation(equation);

        Equation equation2 = new Equation("2x=4");
        equationService.addEquation(equation2);

        Equation equation3 = new Equation("3x=4");
        equationService.addEquation(equation3);

        equationService.addRootsForEquation(equation, Arrays.asList(2.0f, -2.0f));
        List<Equation> equations = equationService.searchByRoot(2.0f);
        assertNotNull(equations);
        assertEquals(1, equations.size());
        assertEquals(equation.getId(), equations.get(0).getId());
        assertEquals(equation.getBody(), equations.get(0).getBody());
    }

    @Test
    void testGetConnection() {
        try {
            Connection connection = equationRepository.getConnection();
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (SQLException | IOException | ClassNotFoundException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
