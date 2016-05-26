package hu.marton.tamas.blackswan.modules;

import android.app.Application;

import dagger.Module;

/**
 * Created by tamas.marton on 26/05/2016.
 */
@Module(
        library = true
)
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }
}
