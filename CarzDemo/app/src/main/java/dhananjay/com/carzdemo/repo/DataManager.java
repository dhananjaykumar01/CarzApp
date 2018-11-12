package dhananjay.com.carzdemo.repo;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dhananjay.com.carzdemo.repo.local.db.IDBHelper;
import dhananjay.com.carzdemo.repo.local.db.model.LocationModel;
import io.reactivex.Observable;


/**
 * Created by dhananjayk on 11-11-2018.
 */

@Singleton
public class DataManager implements IDataManager {

    private final Context mContext;
    private final IDBHelper mDbHelper;


    @Inject
    public DataManager(Context mContext,
                       IDBHelper mDbHelper ) {
        this.mContext = mContext;
        this.mDbHelper = mDbHelper;
     }

    @Override
    public Observable<Boolean> onSaveData(LocationModel locationModel) throws IOException {
        return mDbHelper.onSaveData(locationModel);
    }

    @Override
    public Observable<List<LocationModel>> onGetSavedData() throws IOException {
        return mDbHelper.onGetSavedData();
    }


}