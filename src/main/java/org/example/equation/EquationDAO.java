package org.example.equation;

import java.util.Collection;
import java.util.List;

public interface EquationDAO {
    void saveEquation(Equation equation);
    void saveRoot(long for_eq_id, Float root);
    Equation getEquationById(long id);
    Equation getEquationByBody(String body);
    List<Equation> getEquations();
    List<Equation> getEquationsByRoot(Float root);
    List<Equation> getOnlyOneRootEquations();
    List<Float> getRootsForEquation(long id);
}
