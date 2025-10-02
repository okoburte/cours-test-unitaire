package fr.diginamic.testunitaire.calculator;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        if( b == 0) throw new ArithmeticException("Division par 0");
        return a / b;
    }

    public int pow(int a, int b) {
        return (int) Math.pow(a, b);
    }
}
