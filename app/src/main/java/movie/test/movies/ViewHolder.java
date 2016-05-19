package movie.test.movies;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import movie.test.movies.model.Movie;
import movie.test.movies.model.Tv;

/**
 * Created by muhammad ali
 * on 18/05/2016.
 */
public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected final TextView tv_Title;
    protected final ImageView iv_Poster;
    protected final TextView tv_Rating;
    protected final TextView tv_Year;
    protected final TextView tv_Info;
    protected final TextView tv_MoreInfo;
    protected final ImageView iv_Rating;
    protected final ImageView iv_Cal;
    protected final View v_Line;



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
        v_Line = itemView.findViewById(R.id.v_Line);
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
