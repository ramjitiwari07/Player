package com.utils;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Praveen on 10-Jul-17.
 */

public class OkHttpRequest {

    public static Request getRequest(String URL,RequestBody requestBody,String sessionToken){
        Request request=new Request.Builder()
                .header("token",sessionToken)
                .header("Accept", Constant.HeaderParams.ACCEPT_TYPE)
                .header("Content-Type", Constant.HeaderParams.CONTENT_TYPE)
                .header("Cache-Control", "no-cache")
                .url(URL)
                .post(requestBody)
                .build();


        return request;

    }


    public static Request getRequestGet(String URL,String sessionToken){
        Request request=new Request.Builder()
               /* .header("token",sessionToken)*/
                .header("Accept", Constant.HeaderParams.ACCEPT_TYPE)
                .header("Content-Type", Constant.HeaderParams.CONTENT_TYPE)
                .header("Cache-Control", "no-cache")
                .url(URL)
                /*.post(requestBody)*/
                .build();

        return request;

    }
}
