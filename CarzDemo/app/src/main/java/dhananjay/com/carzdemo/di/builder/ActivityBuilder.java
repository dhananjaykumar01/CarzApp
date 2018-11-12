package dhananjay.com.carzdemo.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dhananjay.com.carzdemo.ui.MapsActivity;
import dhananjay.com.carzdemo.ui.MapsModule;

/**
 * Created by dhananjayk on 11-11-2018.
 */
@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = MapsModule.class)
    abstract MapsActivity bindMapsActivity();
}
