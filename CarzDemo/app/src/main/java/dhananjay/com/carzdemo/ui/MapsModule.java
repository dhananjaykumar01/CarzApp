package dhananjay.com.carzdemo.ui;

import dagger.Module;
import dagger.Provides;
import dhananjay.com.carzdemo.repo.IDataManager;
import dhananjay.com.carzdemo.utils.rx.ISchedulerProvider;

/**
 * Created by dhananjayk on 11-11-2018.
 */
@Module
public class MapsModule {

    @Provides
    MapsViewModel provideMapsViewModel(IDataManager dataManager,
                                         ISchedulerProvider schedulerProvider) {
        return new MapsViewModel(dataManager, schedulerProvider);
    }
}
