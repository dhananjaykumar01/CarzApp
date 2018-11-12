package dhananjay.com.carzdemo.ui;

import android.databinding.ObservableField;
import android.util.Log;

import java.util.List;

import dhananjay.com.carzdemo.repo.IDataManager;
import dhananjay.com.carzdemo.repo.local.db.model.LocationModel;
import dhananjay.com.carzdemo.ui.base.BaseViewModel;
import dhananjay.com.carzdemo.utils.rx.ISchedulerProvider;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * Created by dhananjayk on 11-11-2018.
 */

public class MapsViewModel extends BaseViewModel<IMapsNavigator> {
    LocationModel locationModel = new LocationModel();
    ObservableField<String> sLatitude = new ObservableField<>("");
    ObservableField<String> sLongitude = new ObservableField<>("");
    ObservableField<String> dLatitude = new ObservableField<>("");
    ObservableField<String> dLongitude = new ObservableField<>("");

    public MapsViewModel(IDataManager dataManager, ISchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onSave() {
        try {

            getCompositeDisposable().add(getDataManager().
                    onSaveData(new LocationModel(sLatitude.toString(), sLongitude.toString(), dLatitude.toString(), dLongitude.toString()))
                    .observeOn(getSchedulerProvider().ui())
                    .observeOn(getSchedulerProvider().io()).
                            subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean aBoolean) {
                                    getSaveData();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) {

                                }
                            }));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());


        }
        getNavigator().sendLocation(sLatitude,sLongitude,dLatitude,dLongitude);
    }

    private void getSaveData() {
        try {
            getCompositeDisposable().add(getDataManager().
                    onGetSavedData()
                    .observeOn(getSchedulerProvider().ui())
                    .observeOn(getSchedulerProvider().io()).
                            subscribe(new Consumer<List<LocationModel>>() {
                                @Override
                                public void accept(List<LocationModel> aBoolean) {

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) {

                                }
                            }));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());


        }
    }

    public ObservableField<String> getsLatitude() {
        if (sLatitude != null) {
            // locationModel.setsLatitude(sLatitude);
        }
        return sLatitude;
    }

    public ObservableField<String> getsLongitude() {
        if (sLongitude != null) {
            // locationModel.setsLongitude(sLongitude);
        }
        return sLongitude;
    }

    public ObservableField<String> getdLatitude() {
        if (dLatitude != null) {
            // locationModel.setdLatitude(dLatitude);
        }
        return dLatitude;
    }

    public ObservableField<String> getdLongitude() {
        if (dLongitude != null) {
            //locationModel.setdLongitude(dLongitude);
        }
        return dLongitude;
    }

}
