package hu.marton.tamas.movie;

import android.app.Application;

import dagger.ObjectGraph;
import hu.marton.tamas.movie.modules.ApplicationModule;
import hu.marton.tamas.movie.modules.NetworkModule;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class MovieApplication extends Application {

    public static String API_KEY = "0a08e38b874d0aa2d426ffc04357069d";
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        setupObjectGraph();
    }

    /**
     * setup objectGraph for dependency handling
     */
    private void setupObjectGraph() {
        objectGraph = ObjectGraph.create(new ApplicationModule(this), new NetworkModule());
    }

    public ObjectGraph getApplicationGraph() {
        return objectGraph;
    }
}
