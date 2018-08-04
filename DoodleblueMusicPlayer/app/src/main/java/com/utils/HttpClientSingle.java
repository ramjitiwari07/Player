package com.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Praveen on 10-Jul-17.
 */

public class HttpClientSingle {

    public static OkHttpClient okHttpClient= new OkHttpClient.Builder()
            .connectTimeout(240, TimeUnit.SECONDS)
            .readTimeout(240, TimeUnit.SECONDS)
            .writeTimeout(240, TimeUnit.SECONDS)
            .build();

    public static OkHttpClient getClient(){

        if(okHttpClient!=null){
            return okHttpClient;
        }

        else{
            okHttpClient=new OkHttpClient();
            return okHttpClient;
        }
    }
}
