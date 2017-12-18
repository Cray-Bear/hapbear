package com.fty1.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

    public static String toJsonString(Object object){
        return JSONObject.toJSONString(object, SerializerFeature.WriteNullStringAsEmpty);
    }


}
