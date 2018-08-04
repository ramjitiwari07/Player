package com.example.musicdb;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.android.db");
        createTribalSchema(schema);
        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }
    private static void createTribalSchema(Schema schema)
    {
        Entity songsList = schema.addEntity("SongsList");
        songsList.addStringProperty("songName");
        songsList.addStringProperty("thumImage");
        songsList.addStringProperty("url");
    }
}
