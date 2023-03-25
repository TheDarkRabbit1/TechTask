package org.example.equation;

import java.util.List;

public interface EquationDAO {
    public void save(Equation equation);
    public Equation getEquationById();
    public List<Equation> getEquations();
}
