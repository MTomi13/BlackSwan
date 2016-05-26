package hu.marton.tamas.blackswan.modules;

import dagger.Module;

/**
 * Created by tamas.marton on 26/05/2016.
 */
@Module(
        includes = {
                ApplicationModule.class,
        },
        library = true
)
public class NetworkModule {
}
