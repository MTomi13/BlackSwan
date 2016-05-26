package hu.marton.tamas.blackswan.modules;

import dagger.Module;
import hu.marton.tamas.blackswan.BlackSwanActivity;
import hu.marton.tamas.blackswan.HomeActivity;

/**
 * Created by tamas.marton on 26/05/2016.
 */
@Module(
        injects = {
                BlackSwanActivity.class,
                HomeActivity.class
        },
        addsTo = NetworkModule.class,
        library = true
)
public class ActivityModule {

    private final BlackSwanActivity blackSwanActivity;

    public ActivityModule(BlackSwanActivity blackSwanActivity) {
        this.blackSwanActivity = blackSwanActivity;
    }
}
