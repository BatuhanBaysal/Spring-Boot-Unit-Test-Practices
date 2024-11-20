package com.batuhan.testing.StartUnitTest;

import java.util.List;

public class Start {
    private final String name = "Batuhan Baysal";
    private final String nameDuplicate = name;
    private final String[] alphabet = {"A", "B", "C"};
    private final List<String> nameInList = List.of("Batu", "han", "Baysal");

    public String getName() {
        return name;
    }

    public String getNameDuplicate() {
        return nameDuplicate;
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public List<String> getNameInList() {
        return nameInList;
    }

    public int addNumbers(int a, int b) {
        return a + b;
        // return a + b + b; // wrong sample to try.
    }

    public int multiplyNumbers(int a, int b) {
        return a * b;
    }

    public Object checkNull(Object obj) {
        if (obj != null) {
            return obj;
        }
        return null;
    }

    public Boolean isGreater(int n1, int n2) {
        if (n1 > n2) {
            return true;
        }
        return false;
    }

    public String throwException(int a) throws Exception {
        if (a < 0) {
            throw new Exception("Value should be greater than or equal to 0");
        }
        return "Value is greater than or equal to 0";
    }

    public void checkTimeout() throws InterruptedException {
        System.out.println("I am going to sleep");
        Thread.sleep(2000);
        System.out.println("Sleeping over");
    }
}