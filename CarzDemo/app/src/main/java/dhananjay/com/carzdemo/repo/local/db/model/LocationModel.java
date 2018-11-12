package dhananjay.com.carzdemo.repo.local.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.ObservableField;

/**
 * Created by dhananjayk on 11-11-2018.
 */

@Entity
public class LocationModel {

    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "sLat")
    public String sLatitude;
    @ColumnInfo(name = "sLng")
    public String sLongitude;
    @ColumnInfo(name = "dLat")
    public String dLatitude;
    @ColumnInfo(name = "dLng")
    public String dLongitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsLatitude() {
        return sLatitude;
    }

    public void setsLatitude(String sLatitude) {
        this.sLatitude = sLatitude;
    }

    public String getsLongitude() {
        return sLongitude;
    }

    public void setsLongitude(String sLongitude) {
        this.sLongitude = sLongitude;
    }

    public String getdLatitude() {
        return dLatitude;
    }

    public void setdLatitude(String dLatitude) {
        this.dLatitude = dLatitude;
    }

    public String getdLongitude() {
        return dLongitude;
    }

    public void setdLongitude(String dLongitude) {
        this.dLongitude = dLongitude;
    }

    public LocationModel(String sLatitude, String sLongitude, String dLatitude, String dLongitude) {

        this.sLatitude = sLatitude;
        this.sLongitude = sLongitude;
        this.dLatitude = dLatitude;
        this.dLongitude = dLongitude;
    }

    public LocationModel() {

    }


}





