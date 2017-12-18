package com.fty1.oss.core;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;

import java.util.List;

public class OssOperation {

    public void ls(OSSClient ossClient){




        List<Bucket> bucketList = ossClient.listBuckets();
        if(bucketList == null || bucketList.isEmpty()){
            System.out.println("No Bucket!");
        }
        for(Bucket bucket:bucketList){
            System.out.println(bucket.getName());
        }

    }

}
