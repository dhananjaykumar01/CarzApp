package dhananjay.com.carzdemo.repo.local.db.db_utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import dhananjay.com.carzdemo.repo.local.db.dao.LocationDao;
import dhananjay.com.carzdemo.repo.local.db.model.LocationModel;


/**
 * Created by dhananjayk on 11-11-2018.
 */
@Database(entities = {LocationModel.class}, version = 1, exportSchema = false)

public abstract class CarzDemoDatabase extends RoomDatabase {

    public abstract LocationDao locationDao();


}