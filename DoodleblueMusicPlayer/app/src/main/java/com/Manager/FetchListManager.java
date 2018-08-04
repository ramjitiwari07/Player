package com.Manager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.android.db.DaoSession;
import com.android.db.SongsList;
import com.android.db.SongsListDao;
import com.utils.Constant;
import com.utils.HttpClientSingle;
import com.utils.OkHttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 * Created by ramji tiwari on 07-06-2018.
 */

public class FetchListManager {
    String responseStr="";
    Context context;
    public FetchListManager(Context context){
        this.context=context;
    }


    public void GetAllSongList(final CallBackManager callBackManager)
    {
        String Base_Url="https://raw.githubusercontent.com/bhagyaAndroid/sampleJson/master/song.json";
        OkHttpClient okHttpClient= HttpClientSingle.getClient();
        Request request= OkHttpRequest.getRequestGet(Base_Url,"");
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.i("responce","failure"+e.toString());
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBackManager.onFailure(e.toString());
                    }
                });
            }
            @Override
            public void onResponse(Call call,final Response response) throws IOException {
                responseStr=response.body().string();
                String test= Integer.toString(response.code());
                Log.i("test","test "+test);
                final String parse_str=parsedataben(responseStr,test);
                Log.i("responce","success="+responseStr);
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callBackManager.onSuccess(parse_str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public String parsedataben(String jsonResponse,String response) {
        String status="",responseString="";
        DaoSession daoSession= BaseManager.getDBSessoin(context);
        SongsListDao songsListDao=daoSession.getSongsListDao();
        songsListDao.deleteAll();
        try {
            if (jsonResponse != null && !jsonResponse.equals(null)) {
                Object object = new JSONTokener(jsonResponse).nextValue();
                if (object instanceof JSONObject)
                {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    responseString = status;
                    JSONArray jsa1 = jsonObject.getJSONArray("songs");
                    if (jsa1.length()>0)
                    {
                        for (int j = 0; j < jsa1.length(); j++)
                        {
                            JSONObject jsonObject2 = jsa1.getJSONObject(j);
                            String songName = jsonObject2.optString("songName");
                            String thumImage = jsonObject2.optString("thumImage");
                            String url = jsonObject2.optString("url");

                            SongsList songsList = new SongsList(songName,thumImage,url);
                            songsListDao.insertOrReplace(songsList);
                        }
                    }
                    else
                    {
                        responseString = "Songs is not available.";
                    }

                } else {
                    responseString = "Received data is not compatible.";
                }
            } else {
                responseString = "Please check your internet connection.";
            }
        } catch (JSONException e) {
            responseString = Constant.Messages.SERVER_RESPONCE;
            e.printStackTrace();
        }
        return responseString;
    }


}
