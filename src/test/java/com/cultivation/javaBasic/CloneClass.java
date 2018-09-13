package com.cultivation.javaBasic;

public class CloneClass {
    public String name;
    public int year;



    public CloneClass(String name,int year){
        this.name=name;
        this.year=year;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
       CloneClass cloneObject=new CloneClass(name,year);
       return cloneObject;
    }
}
