package com.cultivation.javaBasic.util;

public class LocalClassUpdateField {
    private int year;

    public LocalClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    public int somethingHappen() {
        class localClass {
            public int add() {
                return ++year;
            }
        }
        return new localClass().add();
    }
}