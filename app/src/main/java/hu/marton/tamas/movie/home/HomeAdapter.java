package hu.marton.tamas.movie.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import hu.marton.tamas.movie.BaseViewHolder;
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
        holder.description.setText(resultWrapper.getOverview());
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

    class HomeViewHolderHandler extends BaseViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.rate)
        TextView rate;

        @BindView(R.id.year)
        TextView year;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.more_info)
        TextView moreInfo;

        @BindView(R.id.separator)
        View separator;

        @BindView(R.id.card_imageview)
        ImageView image;

        public HomeViewHolderHandler(final View itemView) {
            super(itemView);
        }
    }

    public interface ResultWrapperClickListener {

        void resultWrapperClicked(ResultWrapper resultWrapper, ContentType contentType);
    }
}
