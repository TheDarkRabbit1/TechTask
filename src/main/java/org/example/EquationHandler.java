package org.example;

import java.util.regex.Pattern;

public class EquationHandler {
    private String str;
    private boolean viableEquation = true;

    public EquationHandler(String str) {
        this.str = str;
        if(str!=null){

            checkNullEmpty();
            symbolsRegexOnString();
            bracketBalance();
            doubleOperationHandler();
        }else{
            this.viableEquation=false;
            System.out.println("The Equation is Null");
        }
    }
    private void checkNullEmpty(){
        if (str.isEmpty()){
            viableEquation=false;
            System.out.println("Equation is empty");
        }else{this.str=str.replaceAll("\\s+", "");}
    }

    private void symbolsRegexOnString() {
        if (!this.str.contains("="))
            this.viableEquation=false;
        String regex = "^[/*\\-+^.\\da-z=]+$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(str).matches()) {
            viableEquation = false;
            System.out.println("unexpected symbol, for String: "+this.str);
        }
    }

    private void bracketBalance() {
        if ((str.chars().filter(c -> c == '(').count() - str.chars().filter(c -> c == ')').count())!=0){
            viableEquation = false;
            System.out.println("brackets aren't balanced:"+str.chars().filter(c -> c == '(').count()
                    +" "+str.chars().filter(c -> c == ')').count());
        }
    }

    private void doubleOperationHandler() {
        char[] eqInChars = this.str.toLowerCase().toCharArray();
        Pattern firstSigns = Pattern.compile("[+\\-*/]");
        Pattern secondSigns = Pattern.compile("(?<![\\+\\-\\(])[*/]");
        for (int i = 0; i < eqInChars.length-1; i++) {
            Character current=eqInChars[i], next=eqInChars[i+1];
            if (firstSigns.matcher(current.toString()).matches() && secondSigns.matcher(next.toString()).matches()) {
                viableEquation = false;
                System.out.println("Two symbols in a row: " + current+ next);
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
