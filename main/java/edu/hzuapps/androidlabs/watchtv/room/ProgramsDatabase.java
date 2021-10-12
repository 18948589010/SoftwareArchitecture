package edu.hzuapps.androidlabs.watchtv.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Programs.class},version = 1,exportSchema = false)
public abstract class ProgramsDatabase extends RoomDatabase {

    public abstract ProgramsDao getProgramsDao();

    private static ProgramsDatabase INSTANCE;
    public static synchronized ProgramsDatabase getINSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder
                    (context.getApplicationContext(),ProgramsDatabase.class,"programe_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
