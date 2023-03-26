package org.example.equation;

import org.matheclipse.parser.client.eval.DoubleEvaluator;

import java.util.List;

public class EquationService {
    private final EquationRepository equationRepository;

    public EquationService() {
        this.equationRepository = new EquationRepository();
    }

    public Equation getEquation(long id) {
        return equationRepository.getEquationById(id);
    }
    public Equation getEquationByBody(String body){
        return equationRepository.getEquationByBody(body);
    }

    public void addEquation(Equation equation) {
        equationRepository.saveEquation(equation);
    }

    public void addRootsForEquation(Equation equation, List<Float> roots) {
        roots.stream()
                .filter(r -> isRootFitsForEquation(equation, r))
                .forEach(r -> equationRepository.saveRoot(equation.getId(), r));
    }

    public boolean isRootFitsForEquation(Equation equation, Float root) {
        DoubleEvaluator engine = new DoubleEvaluator();
        String[] sides = equation.getBody().split("=");
        engine.defineVariable("x", root);
        try {
            double leftSide = engine.evaluate(sides[0]);
            double rightSide = engine.evaluate(sides[1]);
            return Math.abs(leftSide - rightSide) < 1e-6;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Equation> searchByRoot(Float root) {
        return equationRepository.getEquationsByRoot(root);
    }

    public List<Equation> oneRootOnlyEquations() {
        return equationRepository.getOnlyOneRootEquations();
    }

    public List<Equation> getEqations() {
        return equationRepository.getEquations();
    }
}
