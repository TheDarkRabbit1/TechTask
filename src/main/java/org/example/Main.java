package org.example;

import org.example.equation.Equation;
import org.example.equation.EquationService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final static EquationService equationService = new EquationService();
    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("PROGRAM STARTED" + "\n Menu:");
        while (true) {
            MainMenu();
            switch (scanner.nextInt()) {
                case 1 -> addEquationMenu();
                case 2 -> addRootsForEquationMenu();
                case 3 -> searchMenu();
                case 4 -> getAllEquatinsMenu();
                case 0 -> {return;}
                default -> System.out.println("No such option in menu");
            }
        }
    }
    private static void addRootsForEquationMenu(){
        System.out.println("Enter roots ():");
        List<Float> roots = Arrays.stream(scanner.nextLine().split(" "))
                .filter(s -> isFloat(s))
                .map(Float::parseFloat)
                .collect(Collectors.toList());
        System.out.println("Enter equation body or id:");
        String equationBody=scanner.nextLine();
        if (isLong(equationBody)){
            equationService.addRootsForEquation(equationService.getEquation(
                    Long.parseLong(equationBody)),
                    roots);
        }else{
            equationService.addRootsForEquation(equationService.getEquation(
                    equationService.getEquationByBody(equationBody).getId()),
                    roots);
        }
    }
    private static boolean isFloat(String s){
        try {Float.parseFloat(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    private static boolean isLong(String s){
        try{
            Long.parseLong(s);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    private static void getAllEquatinsMenu(){
        equationService.getEqations().forEach(e -> System.out.print(e.toString()));
    }
    private static void addEquationMenu(){
        System.out.println("Input equation:");
        Equation equation = new Equation(scanner.nextLine());
        equationService.addEquation(equation);
        System.out.println("Equation is viable:" + equation.isViable()
                + " contains " + equation.getNumbers() + " numbers");
    }
    private static void searchMenu(){
        System.out.println("1 - Root:" + "\n 2 - Has only one root");
        switch (scanner.nextInt()) {
            case 1 -> {
                System.out.println("enter root:");
                equationService.searchByRoot(scanner.nextFloat())
                        .forEach(e-> System.out.print(e.toString()));
            }
            case 2 -> equationService.oneRootOnlyEquations().forEach(e -> System.out.print(e.toString()));
            default -> System.out.println("not an option");
        }
    }
    private static void MainMenu(){
        System.out.println("1 - Add new Equation");
        System.out.println("2 - Add roots for Equation");
        System.out.println("3 - Search Equation by:");
        System.out.println("4 - Get all Equations");
    }
}