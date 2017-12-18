package com.fty1.oss;

import com.aliyun.oss.OSSClient;
import com.fty1.oss.core.OssClientManager;
import com.fty1.oss.core.OssOperation;

import java.util.Scanner;

public class OssTest {


    private static final String OSS_CONFIG_ENDPOINT = "-e";
    private static final String OSS_CONFIG_ACCESSKEYID = "-n";
    private static final String OSS_CONFIG_ACCESSKEYSECRET = "-s";
    private static final String OSS_CONFIG_BUCKETNAME = "-u";


    public static void main(String[] args) {

        OssArg ossArg = new OssArg();
        for (int i =0;i < args.length; i++){
            System.out.println(args[i]);

            String cmd = args[i];
            if(cmd.equals(OSS_CONFIG_ENDPOINT)){
                ossArg.setEndpoint(args[i+1]);
            }

            if(cmd.equals(OSS_CONFIG_ACCESSKEYID)){
                ossArg.setAccessKeyId(args[i+1]);
            }

            if(cmd.equals(OSS_CONFIG_ACCESSKEYSECRET)){
                ossArg.setAccessKeySecret(args[i+1]);
            }

            if(cmd.equals(OSS_CONFIG_BUCKETNAME)){
                ossArg.setBucketName(args[i+1]);
                ossArg.setType(BucketEnum.appoint);
            }
        }
        System.out.println(ossArg.toString());

        OssClientManager ossClientManager = new OssClientManager();
        OSSClient ossClient = ossClientManager.open(ossArg);

        OssOperation ossOperation = new OssOperation();
        Scanner reader=new Scanner(System.in);
        boolean isComeFirst = true;
        boolean isQuit = false;

        while (true){

            if(isComeFirst){
                System.out.println("Welcome to Oss Console!");
            }

            System.out.printf(">:");
            isComeFirst = false;
            String cmd = reader.nextLine();
            System.out.println(cmd);

            OssCmdEnum cmdEnum = OssCmdEnum.valueOf(cmd);

            switch (cmdEnum){


                case ls:{
                    ossOperation.ls(ossClient);
                    break;
                }

                case quit:{
                    isQuit = true;
                    break;
                }
            }
            
            if(isQuit){
                System.out.println("ByeBye");
                break;
            }
        }
        reader.close();
    }
}
