package com.dm.base.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class myHttpUtil {
    public static String addParams(String url) throws IOException {
        // 创建一个 HttpClient 对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建一个 HttpGet 对象
        HttpGet httpget = new HttpGet(url);

        // Executing the Get request
        HttpResponse httpresponse = httpclient.execute(httpget);
        HttpEntity entity = httpresponse.getEntity();
        String result = EntityUtils.toString(entity,"utf-8");

        // 下面煮熟这种访问有问题，问题原因未知
//        Scanner sc = new Scanner(entity.getContent(), "utf-8");
//        String result = "";
//        while(sc.hasNext()){
//            result += sc.nextLine();
//        }
//        sc.close();
        httpclient.close();
        return result;
    }
}
