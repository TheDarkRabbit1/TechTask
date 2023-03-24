package org.example;

import java.util.regex.Pattern;

public class EquationHandler {
    private final String str;
    private boolean viableEquation = true;

    public EquationHandler(String str) {
        this.str = str.replaceAll("\\s+", "");
        checkNullEmpty();
        symbolsRegexOnString();
        bracketBalance();
        doubleOperationHandler();
    }
    private void checkNullEmpty(){
        if (str==null || str.isEmpty()){
            viableEquation=false;
            throw new RuntimeException("Equation is null or empty");
        }
    }

    private void symbolsRegexOnString() {
        String regex = "^[/*\\-+^.\\da-z=]+$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(str).matches()) {
            viableEquation = false;
            throw new RuntimeException("unexpected symbol, for String: "+this.str);
        }
    }

    private void bracketBalance() {
        if ((str.chars().filter(c -> c == '(').count() - str.chars().filter(c -> c == ')').count())!=0){
            viableEquation = false;
            throw new RuntimeException("brackets aren't balanced");
        }
    }

    private void doubleOperationHandler() {
        char[] eqInChars = this.str.toLowerCase().toCharArray();
        for (int i = 0; i < eqInChars.length; i++) {
            if ((eqInChars[i] == '+' || eqInChars[i] == '-' || eqInChars[i] == '*' || eqInChars[i] == '/')
                    && (!(Character.isDigit(eqInChars[i + 1]) || eqInChars[i + 1] == 'x'))) {
                viableEquation = false;
                throw new RuntimeException("2 symbols in a row");
                //TODO don't count for *- /-
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
