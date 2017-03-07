package hu.marton.tamas.movie;

import android.view.View;

import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by tamas.marton on 07/03/2017.
 */

public abstract class BaseViewHolder extends ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
