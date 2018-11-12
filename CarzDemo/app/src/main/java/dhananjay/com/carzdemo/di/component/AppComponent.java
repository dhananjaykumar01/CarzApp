package dhananjay.com.carzdemo.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dhananjay.com.carzdemo.CarzDemoApplication;
import dhananjay.com.carzdemo.di.builder.ActivityBuilder;
import dhananjay.com.carzdemo.di.module.AppModule;

/**
 * Created by dhananjayk on 11-11-2018.
 */


@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(CarzDemoApplication app);
}
