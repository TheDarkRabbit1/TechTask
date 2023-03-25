package org.example.equation;

import java.util.regex.Pattern;

public class Equation {
    private String str;
    private boolean viableEquation = true;

    public Equation(String str) {
        this.str = str;
        if (str != null) {
            checkNullEmpty();
            symbolsRegexOnString();
            bracketBalance();
            doubleOperationHandler();
        } else {
            this.viableEquation = false;
            System.out.println("The Equation is Null");
        }
    }

    private void checkNullEmpty() {
        if (str.isEmpty()) {
            viableEquation = false;
            System.out.println("Equation is empty");
        } else {
            this.str = str.replaceAll("\\s+", "");
        }
    }

    private void symbolsRegexOnString() {
        if (!this.str.contains("=")) {
            System.out.println("No '=' in equation");
            this.viableEquation = false;
        }
        String regex = "^[/*\\-+^.\\da-z=()]+$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(str).matches()) {
            viableEquation = false;
            System.out.println("unexpected symbol, for String: " + this.str);
        }
    }

    private void bracketBalance() {
        int opencount=0, closecount=0;
        for (char c :this.str.toCharArray()){
            if (c=='(')
                opencount++;
            else if (c==')'){
                closecount++;
                if (opencount<closecount) {
                    this.viableEquation = false;
                    System.out.println("')' is going before '('");
                }
            }
        }
    }

    private void doubleOperationHandler() {
        char[] eqInChars = this.str.toLowerCase().toCharArray();
        Pattern firstSigns = Pattern.compile("[+\\-*/]");
        Pattern secondSigns = Pattern.compile("(?<![+\\-(])[*/]");
        for (int i = 0; i < eqInChars.length - 1; i++) {
            Character current = eqInChars[i], next = eqInChars[i + 1];
            if (firstSigns.matcher(current.toString()).matches() && secondSigns.matcher(next.toString()).matches()) {
                viableEquation = false;
                System.out.println("Two symbols in a row: " + current + next);
            }
        }
    }

    public String getStr() {
        return str;
    }
    public long getNumbers(){
        Pattern pattern = Pattern.compile("\\d+");
        return pattern.matcher(this.str).results().count();
    }

    public boolean isViable() {
        return viableEquation;
    }
}
