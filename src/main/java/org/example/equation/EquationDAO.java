package org.example.equation;

import java.util.List;

public interface EquationDAO {
    void saveEquation(Equation equation);
    void saveRoots(long for_eq_id, List<Float> roots);
    Equation getEquationById();
    List<Equation> getEquations();
}
