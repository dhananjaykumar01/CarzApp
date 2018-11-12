package dhananjay.com.carzdemo.repo.local.db;


import java.io.IOException;
import java.util.List;
import dhananjay.com.carzdemo.repo.local.db.model.LocationModel;
import io.reactivex.Observable;
/**
 * Created by dhananjayk on 11-11-2018.
 */

public interface IDBHelper {
    Observable<Boolean> onSaveData(LocationModel locationModel) throws IOException;

    Observable<List<LocationModel>> onGetSavedData() throws IOException;


}
