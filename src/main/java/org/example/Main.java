package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input equation:");
        String equationString = scanner.nextLine();
        EquationHandler equation = new EquationHandler(equationString);
        System.out.println("Equation is viable:"+equation.isViable()+" contains "+equation.getNumbers()+" numbers");

    }
}