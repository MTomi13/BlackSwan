package hu.marton.tamas.movie;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import dagger.ObjectGraph;
import hu.marton.tamas.movie.modules.ActivityModule;

/**
 * Created by tamas.marton on 26/05/2016.
 * base activity to add the ActivityModule to the ObjectGraph
 */
public class MovieActivity extends AppCompatActivity{

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        activityGraph = ((MovieApplication) getApplicationContext()).getApplicationGraph().plus(new ActivityModule(this));
        inject(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void inject(Object object) {
        activityGraph.inject(object);
    }

    @Override
    protected void onDestroy() {
        activityGraph = null;
        super.onDestroy();
    }
}
