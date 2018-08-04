package com.android.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.android.db.SongsList;

import com.android.db.SongsListDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig songsListDaoConfig;

    private final SongsListDao songsListDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        songsListDaoConfig = daoConfigMap.get(SongsListDao.class).clone();
        songsListDaoConfig.initIdentityScope(type);

        songsListDao = new SongsListDao(songsListDaoConfig, this);

        registerDao(SongsList.class, songsListDao);
    }
    
    public void clear() {
        songsListDaoConfig.getIdentityScope().clear();
    }

    public SongsListDao getSongsListDao() {
        return songsListDao;
    }

}
