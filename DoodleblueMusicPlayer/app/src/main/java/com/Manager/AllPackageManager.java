package com.Manager;

import android.content.Context;
import com.android.db.*;
import java.util.ArrayList;
import java.util.List;


public class AllPackageManager extends BaseManager {

    Context context;
    String responseString = "";

    public AllPackageManager(Context context) {
        this.context = context;
    }


    public List<SongsList> getSongList() {
        DaoSession daoSession = getDBSessoin(context);
        SongsListDao songsListDao = daoSession.getSongsListDao();

        try {
            List<SongsList> songsLists = new ArrayList<>();
            songsLists = songsListDao.loadAll();
            return songsLists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            songsListDao.getDatabase().close();
        }
    }

}
