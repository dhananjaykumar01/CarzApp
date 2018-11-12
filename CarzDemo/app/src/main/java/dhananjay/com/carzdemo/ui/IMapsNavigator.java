package dhananjay.com.carzdemo.ui;

import android.databinding.ObservableField;

import dhananjay.com.carzdemo.repo.local.db.model.LocationModel;

/**
 * Created by dhananjayk on 10-11-2018.
 */

public interface IMapsNavigator {
    void sendLocation(ObservableField<String> sLat,ObservableField<String> sLng,ObservableField<String> dLat,ObservableField<String> dLng);
}
