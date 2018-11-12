package dhananjay.com.carzdemo.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by dhananjayk on 11-11-2018.
 */

public interface ISchedulerProvider {

    Scheduler ui();

    Scheduler io();

}
