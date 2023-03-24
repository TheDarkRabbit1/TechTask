package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input equation:");
        String equation = scanner.nextLine();
        System.out.println("EQ AFTER INPUT:"+equation);
        EquationHandler equationHandler = new EquationHandler(equation);
        System.out.println("Equation is viable:"+equationHandler.isViable());
    }
}