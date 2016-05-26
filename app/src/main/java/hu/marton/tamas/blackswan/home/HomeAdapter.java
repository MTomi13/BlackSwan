package hu.marton.tamas.blackswan.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

import hu.marton.tamas.blackswan.R;
import hu.marton.tamas.blackswan.api.Popular.model.ResponseContent;
import hu.marton.tamas.blackswan.api.Popular.model.Result;
import hu.marton.tamas.blackswan.util.ViewHelper;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolderHandler> {

    private ResponseContent responseContent;
    private final DisplayImageOptions imageOptions;

    public HomeAdapter(ResponseContent responseContent, DisplayImageOptions imageOptions) {
        this.responseContent = responseContent;
        this.imageOptions = imageOptions;
    }

    @Override
    public HomeViewHolderHandler onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        return new HomeViewHolderHandler(view);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolderHandler holder, final int position) {
        List<Result> results = responseContent.getResults();
        holder.rate.setText(String.valueOf(results.get(position).getVoteAverage()));
        switch (responseContent.getContentType()) {
            case MOVIES:
                holder.year.setText(results.get(position).getReleaseDate());
                holder.title.setText(results.get(position).getTitle());
                ImageLoader.getInstance().displayImage(results.get(position).getLogoImageUrl(), holder.image, imageOptions, new SimpleImageLoadingListener());
                break;
            case SERIES:
                holder.title.setText(results.get(position).getName());
                holder.year.setText(results.get(position).getFirstAirDate());
                ImageLoader.getInstance().displayImage(results.get(position).getLogoImageUrl(), holder.image, imageOptions, new SimpleImageLoadingListener());
                break;
            case PEOPLE:
                holder.title.setText(results.get(position).getName());
                ViewHelper.setVisibility(View.GONE, holder.rate, holder.moreInfo, holder.separator);
                ImageLoader.getInstance().displayImage(results.get(position).getProfileImageUrl(), holder.image, imageOptions, new SimpleImageLoadingListener());
                break;
        }
        holder.overView.setText(results.get(position).getOverview());
    }

    @Override
    public int getItemCount() {
        return responseContent.getResults().size();
    }

    protected class HomeViewHolderHandler extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView rate;
        private final TextView year;
        private final TextView overView;
        private final TextView moreInfo;
        private final View separator;
        private final ImageView image;

        public HomeViewHolderHandler(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            rate = (TextView) itemView.findViewById(R.id.rate);
            year = (TextView) itemView.findViewById(R.id.year);
            overView = (TextView) itemView.findViewById(R.id.description);
            moreInfo = (TextView) itemView.findViewById(R.id.more_info);
            separator = itemView.findViewById(R.id.separator);
            image = (ImageView) itemView.findViewById(R.id.card_imageview);
        }
    }
}
