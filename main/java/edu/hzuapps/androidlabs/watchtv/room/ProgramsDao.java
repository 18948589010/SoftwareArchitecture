package edu.hzuapps.androidlabs.watchtv.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProgramsDao {
    @Insert
    void insertPrograms(Programs... programs);

    @Query("SELECT * FROM programs")
    List<Programs> queryAllPrograms();
}
