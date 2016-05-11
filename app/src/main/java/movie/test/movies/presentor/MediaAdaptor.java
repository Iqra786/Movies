package movie.test.movies.presentor;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;


import movie.test.movies.BuildConfig;
import movie.test.movies.R;
import movie.test.movies.executor.BiographyExecutor;
import movie.test.movies.model.MediaInfo;
import movie.test.movies.model.Movie;
import movie.test.movies.model.Person;
import movie.test.movies.model.Tv;
import movie.test.movies.repository.BiographyInteractorsResponse;


/**
 * Created by muhammad ali
 on 09/05/2016.
 */
public class MediaAdaptor extends RecyclerView.Adapter<MediaAdaptor.ViewHolder> implements BiographyInteractorsResponse {

    private final List mData = new ArrayList<>();
    private final BiographyExecutor biographyExecutor = new BiographyExecutor(this);


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.tv_Rating.setVisibility(View.VISIBLE);
        holder.tv_Year.setVisibility(View.VISIBLE);
        holder.tv_MoreInfo.setVisibility(View.VISIBLE);
        holder.iv_Rating.setVisibility(View.VISIBLE);
        holder.iv_Cal.setVisibility(View.VISIBLE);
        holder.v_Line.setVisibility(View.VISIBLE);



        MediaInfo result = (MediaInfo) mData.get(position);

        holder.tv_Title.setText(result.getTitle() != null ? result.getTitle() : "No name");
        holder.tv_Rating.setText(result.getVoteAverage() != null ? String.valueOf(result.getVoteAverage()) : "");
        String releaseDate = String.valueOf(result.getReleaseDate());
        try {
          String year   =convertDateIntoYear(releaseDate);
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
                biographyExecutor.submitJobs(holder.tv_Info, position, result.getId());
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
     *
     * @param dateTime - data and Time
     * @return - uear in YYYY
     * @throws ParseException
     */

    private String convertDateIntoYear(String dateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" , Locale.getDefault());
        java.util.Date date;
        date = format.parse(dateTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy" , Locale.getDefault());
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
        notifyDataSetChanged();
    }

    /**
     * clear all data from the adaptor
     */

    public void clearAdaptor() {
        if (mData.size() > 0) {
            mData.clear();
            notifyDataSetChanged();
        }
    }


    @Override
    public void updateAdaptor(int position, String bio) {
        if (mData.get(position) instanceof Person) {
            Person result = (Person) mData.get(position);
            result.setBio(bio);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tv_Title;
        private final ImageView iv_Poster;
        private final TextView tv_Rating;
        private final TextView tv_Year;
        private final TextView tv_Info;
        private final TextView tv_MoreInfo;
        private final ImageView iv_Rating;
        private final ImageView iv_Cal;
        private final View v_Line;



        public ViewHolder(View itemView) {
            super(itemView);
            tv_Title = (TextView) itemView.findViewById(R.id.tv_Title);
            tv_Title.setOnClickListener(this);
            tv_Rating = (TextView) itemView.findViewById(R.id.tv_Rating);
            tv_Rating.setOnClickListener(this);
            tv_Title.setOnClickListener(this);
            tv_Year = (TextView) itemView.findViewById(R.id.tv_ReleaseYear);
            tv_Year.setOnClickListener(this);
            tv_Info = (TextView) itemView.findViewById(R.id.tv_Info);
            tv_Info.setOnClickListener(this);
            tv_MoreInfo = (TextView) itemView.findViewById(R.id.tv_MoreInfo);
            tv_MoreInfo.setOnClickListener(this);
            iv_Poster = (ImageView) itemView.findViewById(R.id.iv_Poster);
            iv_Poster.setOnClickListener(this);
            iv_Rating = (ImageView) itemView.findViewById(R.id.iv_Star);
            iv_Rating.setOnClickListener(this);
            iv_Cal = (ImageView) itemView.findViewById(R.id.iv_Calender);
            iv_Cal.setOnClickListener(this);
            v_Line =  itemView.findViewById(R.id.v_Line);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_Poster:
                    callDetailActivity(v);
                    break;
                case R.id.tv_Title:
                    callDetailActivity(v);
                    break;
                case R.id.tv_Info:
                    callDetailActivity(v);
                    break;
                case R.id.tv_MoreInfo:
                    callDetailActivity(v);
                    break;
                case R.id.tv_ReleaseYear:
                    callDetailActivity(v);
                    break;
            }
        }


        /**
         * Call next activity
         *
         * @param v - View
         */


        private void callDetailActivity(View v) {
            Intent intent = new Intent(v.getContext().getApplicationContext(), DetailActivity.class);
            if (v.getTag() instanceof Movie || v.getTag() instanceof Tv) {
                intent.putExtra("data", (Serializable) v.getTag());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().getApplicationContext().startActivity(intent);
            }
        }
    }
}
