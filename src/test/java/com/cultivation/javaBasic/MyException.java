package com.cultivation.javaBasic;

public class MyException  {
    public void uncheckedExcetion() {
        throw  new Error();
    }
    public void checkedException() throws Exception {
        throw new Exception();
    }
}
