package movie.test.movies;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;


import movie.test.movies.model.Detail_Info;
import movie.test.movies.model.Person;
import movie.test.movies.presenter.MediaAdapterModel;
import movie.test.movies.presenter.MediaAdaptorPresenter;


/**
 * Created by muhammad ali
 * on 09/05/2016.
 */
public class MediaAdaptor extends RecyclerView.Adapter<ViewHolder> implements MediaAdapterModel {

    private List mData = new ArrayList<>();
    private final MediaAdaptorPresenter mediaAdaptorPresenter = new MediaAdaptorPresenter(this);


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.item_side_menu, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(movie.test.movies.ViewHolder holder, int position) {

        holder.tv_Rating.setVisibility(View.VISIBLE);
        holder.tv_Year.setVisibility(View.VISIBLE);
        holder.tv_MoreInfo.setVisibility(View.VISIBLE);
        holder.iv_Rating.setVisibility(View.VISIBLE);
        holder.iv_Cal.setVisibility(View.VISIBLE);
        holder.v_Line.setVisibility(View.VISIBLE);


        Detail_Info result = (Detail_Info) mData.get(position);

        holder.tv_Title.setText(result.getTitle() != null ? result.getTitle() : "No name");
        holder.tv_Rating.setText(result.getVoteAverage() != null ? String.valueOf(result.getVoteAverage()) : "");
        String releaseDate = String.valueOf(result.getReleaseDate());
        try {
            String year = convertDateIntoYear(releaseDate);
            holder.tv_Year.setText(year);
        } catch (ParseException e) {
            if (BuildConfig.DEBUG) e.getMessage();
        }

        holder.tv_Info.setText(result.getOverview() != null ? String.valueOf(result.getOverview()) : "");
        String imageName = result.getPosterPath();
        String url = String.format("http://image.tmdb.org/t/p/w185%s", imageName);
        Picasso.with(holder.iv_Poster.getContext()).load(url).into(holder.iv_Poster);


        // for Popular Person
        if (mData.get(position) instanceof Person) {
            Person result2 = (Person) mData.get(position);
            holder.tv_Rating.setVisibility(View.GONE);
            holder.tv_Year.setVisibility(View.GONE);
            holder.tv_MoreInfo.setVisibility(View.GONE);
            holder.iv_Rating.setVisibility(View.GONE);
            holder.iv_Cal.setVisibility(View.GONE);
            holder.v_Line.setVisibility(View.GONE);
            Picasso.with(holder.iv_Poster.getContext()).load(url).into(holder.iv_Poster);
            if (result2.getBio() == null) {
                mediaAdaptorPresenter.biographyRequest(position, result.getId());
            } else {
                holder.tv_Info.setText(result2.getBio());
            }
        }

        holder.tv_Title.setTag(mData.get(position));
        holder.tv_Rating.setTag(mData.get(position));
        holder.tv_Year.setTag(mData.get(position));
        holder.tv_Info.setTag(mData.get(position));
        holder.iv_Poster.setTag(mData.get(position));
        holder.tv_MoreInfo.setTag(mData.get(position));
    }


    /**
     * @param dateTime - data and Time
     * @return - year in YYYY
     * @throws ParseException
     */

    private String convertDateIntoYear(String dateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        java.util.Date date;
        date = format.parse(dateTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy", Locale.getDefault());
        return df.format(date);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * set new adaptor
     *
     * @param data - Data Set
     */
    public void setAdaptor(List<?> data) {
        if (data != null) {
            mData.addAll(data);
        }
        this.notifyDataSetChanged();
    }

    /**
     * clear all data from the adaptor
     */

    public void clearAdaptor() {
        if (mData.size() > 0) {
            mData.clear();
            this.notifyDataSetChanged();
        }
    }


    @Override
    public void setBiographyAtIndex(String biography, int position) {
        if (mData.get(position) instanceof Person) {
            Person result = (Person) mData.get(position);
            result.setBio(biography);
            notifyItemChanged(position);
        }

    }

}
