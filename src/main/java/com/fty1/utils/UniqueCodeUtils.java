package com.fty1.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class UniqueCodeUtils {

    /**
     * 参数直接拼接-使用MD5加密策略生成唯一码
     * @param args
     * @return
     */
    public static String md5(String... args){
        StringBuilder stringBuilder = new StringBuilder();
        for(String arg:args){
            stringBuilder.append(arg);
        }
        return DigestUtils.md5Hex(stringBuilder.toString());
    }


    public static void main(String[] args) {

        System.out.println(UniqueCodeUtils.md5("78687","67887878","67868778789"));

    }

}
