package org.example.equation;

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

    private boolean isRootFitsForEquation(Equation equation, Float root) {
        return false;
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
