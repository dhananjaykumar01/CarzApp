package dhananjay.com.carzdemo.repo.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.location.Location;

import java.util.List;

import dhananjay.com.carzdemo.repo.local.db.model.LocationModel;
/**
 * Created by dhananjayk on 11-11-2018.
 */
@Dao
public interface LocationDao {
    /*@Insert(onConflict = OnConflictStrategy.IGNORE)
    Boolean insert(LocationModel locationModel);*/
    @Insert
    void insert(LocationModel note);

    @Query("SELECT * FROM LocationModel")
    List<LocationModel> getAll();

  /*  @Query("SELECT COUNT(*) FROM  EDSResponse WHERE awbNo = :awbNo")
    Long getEDSNewAWbExist(long awbNo);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EDSResponse edsResponses);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<EDSResponse> edsResponses);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertActivityWizard(List<EDSActivityWizard> activityWizards);

    @Query("SELECT * FROM EDSResponse")
    List<EDSResponse> loadAllEDSList();

    @Query("DELETE FROM EDSResponse")
    void nukeTableEDSResponse();*/

/*  *//* *//**//* @Transaction

*//*
*//*  @Query("SELECT EDSResponse.*, EDSActivityWizard.* FROM EDSResponse INNER JOIN EDSActivityWizard ON awb = EDSActivityWizard.awbNo")
     EdsWithActivityList getEdswithActivityList(long awb);*//*

    @Query("SELECT * FROM EDSResponse where awbNo= :awb")
    EdsWithActivityList getEdswithActivityList(long awb);

    @Query("SELECT COUNT(*) FROM EDSResponse   WHERE  + shipmentStatus =:status")
    long getEDSStatusCount(int status);

    @Query("DELETE FROM EDSActivityWizard")
    void nukeTableEDSActivityWizard();

*//*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(EDSTypeResponse edsTypeResponse);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<EDSTypeResponse> edsTypeResponses);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertActivityWizardList(List<ActivityWizard> activityWizards);

    @Query("SELECT * FROM DRSEDSList")
    List<EDSTypeResponse> loadAllEDSList();

    @Query("SELECT COUNT(*) FROM  DRSEDSList WHERE awbNo = :awbNo")
    Long getEDSAWbExist(long awbNo);

    @Query("SELECT COUNT(*) FROM DRSEDSList   WHERE  + shipmentStatus =:status")
    long getEDSStatusCount(int status);
    @Query("DELETE FROM DRSEDSList")
    void nukeTable();*/
}
