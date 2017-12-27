package com.fty1.jvm;

public class StringIntern {


    private static final String TE = "计算机软件";

    public static void main(String[] args) {



        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1); //false


        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2); //true

    }
}
