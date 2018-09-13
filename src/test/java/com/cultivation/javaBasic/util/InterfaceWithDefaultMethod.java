package com.cultivation.javaBasic.util;

public  interface InterfaceWithDefaultMethod {
    default String tellMeTheTruthOfTheUniverse() {
        return "The truth of the universe is " + getTheTruthOfTheUniverse();
    }

    static String test() {
        return "";
    }

    default String getTheTruthOfTheUniverse() {
        return "42";
    }

    Integer test = 0;
}


