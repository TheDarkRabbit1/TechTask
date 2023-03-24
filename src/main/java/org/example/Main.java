package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input equation:");
        String equation = scanner.next();
        EquationHandler equationHandler = new EquationHandler(equation);
        System.out.println("Equation is viable:"+equationHandler.isViable());
    }
}