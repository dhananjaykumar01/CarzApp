package dhananjay.com.carzdemo.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dhananjay.com.carzdemo.di.DatabaseInfo;
import dhananjay.com.carzdemo.repo.DataManager;
import dhananjay.com.carzdemo.repo.IDataManager;
import dhananjay.com.carzdemo.repo.local.db.DBHelper;
import dhananjay.com.carzdemo.repo.local.db.IDBHelper;
import dhananjay.com.carzdemo.repo.local.db.db_utils.CarzDemoDatabase;
import dhananjay.com.carzdemo.utils.Constants;
import dhananjay.com.carzdemo.utils.rx.ISchedulerProvider;
import dhananjay.com.carzdemo.utils.rx.SchedulerProvider;


/**
 * Created by dhananjayk on 11-11-2018.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    ISchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

    @Provides
    @Singleton
    CarzDemoDatabase provideAppDatabase(@DatabaseInfo("Name") String dbName, @DatabaseInfo("Password") String dbPassword, Context context) {
        // SafeHelperFactory safeHelperFactory = new SafeHelperFactory(dbPassword.toCharArray());
        return Room.databaseBuilder(context, CarzDemoDatabase.class, dbName)
                 //      .openHelperFactory(safeHelperFactory)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo("Name")
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }

    @Provides
    @DatabaseInfo("Password")
    String provideDatabaseKey() {
        return Constants.DB_PASS;
    }


    @Provides
    @Singleton
    IDataManager provideDataManager(DataManager dataHelper) {
        return dataHelper;
    }

    @Provides
    @Singleton
    IDBHelper provideDatabaseHelper(DBHelper databaseHelper) {
        return databaseHelper;
    }




}
