package edu.hzuapps.androidlabs.watchtv.room.manager;

import android.content.Context;

import java.util.List;

import edu.hzuapps.androidlabs.watchtv.room.Programs;
import edu.hzuapps.androidlabs.watchtv.room.ProgramsDao;
import edu.hzuapps.androidlabs.watchtv.room.ProgramsDatabase;

public class DBEngine{

    private ProgramsDao programsDao;
    private List<Programs> list;
    private String teststr;


    public DBEngine(Context context) {
        ProgramsDatabase programsDatabase = ProgramsDatabase.getINSTANCE(context);
        programsDao = programsDatabase.getProgramsDao();
    }

    public void insertPrograms(Programs... programs) {
        programsDao.insertPrograms(programs);
    }


      public List<Programs> queryAllPrograms() {
          List<Programs> list = programsDao.queryAllPrograms();
          return list;
      }

//    private static class InsertAsyncTask extends AsyncTask<Programs,Void,Void>{
//
//        private ProgramsDao dao;
//
//        public InsertAsyncTask(ProgramsDao dao) {
//            this.dao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(Programs... programs) {
//            dao.insertPrograms(programs);
//            return null;
//        }
//    }


}
