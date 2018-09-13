package com.cultivation.javaBasic.showYourIntelligence;

import java.util.Arrays;
import java.util.Optional;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start
        StackTraceElement[] stackArrays = new Throwable().getStackTrace();
        Optional<StackTraceElement> exceptionClass = Arrays.stream(stackArrays).filter(stack -> stack.getClassName() == "com.cultivation.javaBasic.ExceptionTest").findFirst();
        return exceptionClass.get().getClassName() + "." + exceptionClass.get().getMethodName();
        // --end-->
    }
}
