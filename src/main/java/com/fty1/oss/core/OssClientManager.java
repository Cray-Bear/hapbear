package com.fty1.oss.core;

import com.aliyun.oss.OSSClient;
import com.fty1.oss.OssArg;

public class OssClientManager {

    private OSSClient ossClient = null;


    public OSSClient open(OssArg ossArg){
        if(ossClient == null){
            return new OSSClient(ossArg.getEndpoint(),ossArg.getAccessKeyId(),ossArg.getAccessKeySecret());
        }
        return ossClient;
    }

    public void close(){
        ossClient.shutdown();
    }
}
