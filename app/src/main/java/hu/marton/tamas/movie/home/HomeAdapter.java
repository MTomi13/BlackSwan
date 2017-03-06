package hu.marton.tamas.movie.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hu.marton.tamas.movie.R;
import hu.marton.tamas.movie.api.Popular.model.ContentType;
import hu.marton.tamas.movie.api.Popular.model.ResponseContent;
import hu.marton.tamas.movie.api.Popular.model.ResultWrapper;
import hu.marton.tamas.movie.util.ViewHelper;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolderHandler> {

    private ResponseContent responseContent;
    private ResultWrapperClickListener resultWrapperClickListener;

    public HomeAdapter(ResponseContent responseContent, ResultWrapperClickListener resultWrapperClickListener) {
        this.responseContent = responseContent;
        this.resultWrapperClickListener = resultWrapperClickListener;
    }

    @Override
    public HomeViewHolderHandler onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        return new HomeViewHolderHandler(view);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolderHandler holder, final int position) {
        List<ResultWrapper> resultWrappers = responseContent.getResultWrappers();
        final ResultWrapper resultWrapper = resultWrappers.get(position);
        final ContentType contentType = responseContent.getContentType();
        holder.rate.setText(String.valueOf(resultWrapper.getVoteAverage()));
        switch (contentType) {
            case MOVIES:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultWrapperClickListener.resultWrapperClicked(resultWrapper, contentType);
                    }
                });
                holder.year.setText(resultWrapper.getReleaseDate());
                holder.title.setText(resultWrapper.getTitle());
                setupImage(holder.image, resultWrapper.getLogoImageUrl());
                break;
            case SERIES:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultWrapperClickListener.resultWrapperClicked(resultWrapper, contentType);
                    }
                });
                holder.title.setText(resultWrapper.getName());
                holder.year.setText(resultWrapper.getFirstAirDate());
                setupImage(holder.image, resultWrapper.getLogoImageUrl());
                break;
            case PEOPLE:
                holder.title.setText(resultWrapper.getName());
                ViewHelper.setVisibility(View.GONE, holder.rate, holder.moreInfo, holder.separator);
                setupImage(holder.image, resultWrapper.getProfileImageUrl());
                break;
        }
        holder.overView.setText(resultWrapper.getOverview());
    }

    private void setupImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_error)
                .into(imageView);

        //TODO: switch off this
        Picasso.with(imageView.getContext()).setIndicatorsEnabled(true);
    }

    @Override
    public int getItemCount() {
        return responseContent.getResultWrappers().size();
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

    public interface ResultWrapperClickListener {

        void resultWrapperClicked(ResultWrapper resultWrapper, ContentType contentType);
    }
}
