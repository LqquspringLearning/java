package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {

    private int year;

    public InnerClassUpdateField(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void add() {

        YearIncrementer yearIncrementer = new YearIncrementer(2);
        yearIncrementer.add();
    }

    @SuppressWarnings("WeakerAccess")
    public class YearIncrementer {

        private  static final int years=2;
        private int year;

        public YearIncrementer() {

        }

        public YearIncrementer(int year) {
            YearIncrementer.this.year = year;
        }

        public void add() {
            InnerClassUpdateField.this.year += this.year;
        }

        public void increment(int year) {
            InnerClassUpdateField.this.year += year;
        }
    }
}
