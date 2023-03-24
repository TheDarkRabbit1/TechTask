package org.example;

import java.util.regex.Pattern;

public class EquationHandler {
    private final String str;
    private boolean viableEquation = true;

    public EquationHandler(String str) {
        this.str = str;
        symbolsRegexOnString();
        bracketBalance();
        doubleOperationHandler();
    }

    private void symbolsRegexOnString() {
        String regex = "[\\d.x/*+()\\-\\s\\^]+\n";
        if (!Pattern.matches(this.str, regex))
            viableEquation = false;
    }

    private void bracketBalance() {
        if ((str.chars().filter(c -> c == '(').count() - str.chars().filter(c -> c == ')').count()) == 0)
            viableEquation = false;
    }

    private void doubleOperationHandler() {
        char[] eqInChars = this.str.toLowerCase().toCharArray();
        for (int i = 0; i < eqInChars.length; i++) {
            if ((eqInChars[i] == '+' || eqInChars[i] == '-' || eqInChars[i] == '*' || eqInChars[i] == '/')
                    && (!(Character.isDigit(eqInChars[i + 1]) || eqInChars[i + 1] == 'x'))) {
                viableEquation = false;
            }
        }
    }

    public String getStr() {
        return str;
    }

    public boolean isViable() {
        return viableEquation;
    }
}
