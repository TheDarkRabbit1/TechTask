package org.example.equation;

import java.util.List;
import java.util.regex.Pattern;

public class Equation {
    private long id;
    private String body;
    private List<Float> roots;
    private boolean viableEquation = true;

    public Equation(String body) {
        this.body = body;
        if (body != null) {
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
        if (body.isEmpty()) {
            viableEquation = false;
            System.out.println("Equation is empty");
        } else {
            this.body = body.replaceAll("\\s+", "");
        }
    }

    private void symbolsRegexOnString() {
        if (!this.body.contains("=")) {
            System.out.println("No '=' in equation");
            this.viableEquation = false;
        }
        String regex = "^[/*\\-+^.\\da-z=()]+$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(body).matches()) {
            viableEquation = false;
            System.out.println("unexpected symbol, for String: " + this.body);
        }
    }

    private void bracketBalance() {
        int opencount = 0, closecount = 0;
        for (char c : this.body.toCharArray()) {
            if (c == '(')
                opencount++;
            else if (c == ')') {
                closecount++;
                if (opencount < closecount) {
                    this.viableEquation = false;
                    System.out.println("')' is going before '('");
                }
            }
        }
    }

    private void doubleOperationHandler() {
        char[] eqInChars = this.body.toLowerCase().toCharArray();
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

    public String getBody() {
        return body;
    }

    public long getNumbers() {
        Pattern pattern = Pattern.compile("\\d+");
        return pattern.matcher(this.body).results().count();
    }

    public boolean isViable() {
        return viableEquation;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Equation{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", roots=" + roots +
                ", viableEquation=" + viableEquation +
                '}';
    }
}
