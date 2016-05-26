package hu.marton.tamas.blackswan;

import android.app.Application;

import dagger.ObjectGraph;
import hu.marton.tamas.blackswan.modules.ApplicationModule;
import hu.marton.tamas.blackswan.modules.NetworkModule;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class BlackSwanApplication extends Application {

    public static String API_KEY = "0a08e38b874d0aa2d426ffc04357069d";
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        setupObjectGraph();
    }

    /**
     * setup objectGraph for dependanc handling
     */
    private void setupObjectGraph() {
        objectGraph = ObjectGraph.create(new ApplicationModule(this), new NetworkModule());
    }

    public ObjectGraph getApplicationGraph() {
        return objectGraph;
    }
}
