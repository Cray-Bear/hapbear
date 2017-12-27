package com.fty1.jvm;

public class StackOFE {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOFE stackOFE = new StackOFE();
        try {
            stackOFE.stackLeak();
        } catch (Throwable throwable) {
            System.out.println("StackOFE.stackLength: "+stackOFE.stackLength);
            throw throwable;
        }



    }
}
