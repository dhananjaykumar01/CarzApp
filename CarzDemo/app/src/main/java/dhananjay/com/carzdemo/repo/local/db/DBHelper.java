package dhananjay.com.carzdemo.repo.local.db;


import android.databinding.ObservableField;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import dhananjay.com.carzdemo.repo.local.db.db_utils.CarzDemoDatabase;
import dhananjay.com.carzdemo.repo.local.db.model.LocationModel;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by dhananjayk on 11-11-2018.
 */

@Singleton
public class DBHelper implements IDBHelper {

    private final CarzDemoDatabase mAppDatabase;

    @Inject
    public DBHelper(CarzDemoDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }



    @Override
    public Observable<Boolean> onSaveData(LocationModel locationModel) throws IOException {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
               mAppDatabase.locationDao().insert(locationModel);
                return true;
            }
        });
    }

    @Override
    public Observable<List<LocationModel>> onGetSavedData() throws IOException {
        return Observable.fromCallable(new Callable<List<LocationModel>>() {
            @Override
            public List<LocationModel> call() {

                return mAppDatabase.locationDao().getAll();
            }
        });
    }


}

