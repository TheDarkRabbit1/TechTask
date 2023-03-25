package org.example;

import org.example.equation.Equation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PROGRAM STARTED"+"\n Menu:");
        while (true){
            System.out.println("1 - Add new Equation");
            System.out.println("2 - ");
            switch (scanner.nextInt()){
                case 1:
                    System.out.println("Input equation:");
                    Equation equation = new Equation(scanner.nextLine());
                    System.out.println("Equation is viable:"+equation.isViable()+" contains "+equation.getNumbers()+" numbers");
                    addToDB(equation);
                    break;
                case 2:

                    break;
            }
        }
    }
    private static void addToDB(Equation equation){
        if (equation.isViable()){

        }
    }
}