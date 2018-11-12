package dhananjay.com.carzdemo.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import java.lang.ref.WeakReference;

import dhananjay.com.carzdemo.repo.IDataManager;
import dhananjay.com.carzdemo.utils.rx.ISchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by dhananjayk on 11-11-2018.
 */

public abstract class BaseViewModel<N> extends ViewModel {

    private WeakReference<N> mNavigator;
    private final IDataManager mDataManager;
    private final ISchedulerProvider mSchedulerProvider;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(IDataManager dataManager,
                         ISchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public IDataManager getDataManager() {
        return mDataManager;
    }

    public ISchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }



    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }



}
