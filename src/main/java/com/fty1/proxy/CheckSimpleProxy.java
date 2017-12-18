package com.fty1.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.*;


@Component
public class CheckSimpleProxy {


    private Logger logger = LoggerFactory.getLogger(CheckSimpleProxy.class);

    public boolean proxyCheck(String ip,String port){
        return isOK(ip,port);
    }


    public static void main(String[] args) {


    /*    101.236.43.226	8080
        101.236.61.82	8080
        101.236.63.64	8080
        101.236.22.20	8080
        101.236.62.196	8080
        101.236.50.118	8080
        101.236.53.150	8080
        101.236.36.130	8080
        101.236.54.80	8080
        101.236.54.234	8080
        101.236.60.215	8080*/


        CheckSimpleProxy checkSimpleProxy = new CheckSimpleProxy();
        boolean isOK=checkSimpleProxy.isOK("101.236.43.226","8080");

        if(isOK) {
            System.out.println("is OK");
        }

    }


    private boolean isOK(String host,String port){

        SocketAddress addr = new InetSocketAddress(host, Integer.parseInt(port));
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
        try{
            URL url = new URL("http://www.baidu.com");
            URLConnection conn = url.openConnection(proxy);
            conn.setConnectTimeout(500);
            conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; NT 5.1; GTB5; .NET CLR 2.0.50727; CIBA)");
            Object res = conn.getContent();
            System.out.println(res);
            if(res != null){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

}
