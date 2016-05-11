package movie.test.movies.presentor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import movie.test.movies.R;
import movie.test.movies.model.MediaInfo;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Details");
        MediaInfo info = (MediaInfo) getIntent().getSerializableExtra("data");


        TextView tv_Title = (TextView) findViewById(R.id.tv_Title);


        if (tv_Title != null) {
            tv_Title.setText(info.getTitle());
        }

        TextView tv_Rating = (TextView) findViewById(R.id.tv_Rating);
        if (tv_Rating != null && info.getVoteAverage() != null) {
            String a_Vote = String.valueOf(info.getVoteAverage()) + "/ 10";
            tv_Rating.setText(a_Vote);
        }


        TextView tv_Year = (TextView) findViewById(R.id.tv_ReleaseYear);
        if (tv_Year != null) {
            tv_Year.setText(info.getReleaseDate() != null ? info.getReleaseDate() : "");
        }


        TextView tv_Info = (TextView) findViewById(R.id.tv_Info);
        if (tv_Info != null) {
            tv_Info.setText(info.getOverview() != null ? info.getOverview() : "");
        }

        TextView tv_Votes = (TextView) findViewById(R.id.tv_Votes);
        if (tv_Votes != null) {
            tv_Votes.setText(String.valueOf(info.getVoteCount()));
        }
        ImageView iv_Poster = (ImageView) findViewById(R.id.iv_Poster);
        if (iv_Poster != null) {
            String imageName = info.getPosterPath();
            String url = String.format("http://image.tmdb.org/t/p/w185%s", imageName);
            Picasso.with(iv_Poster.getContext()).load(url).into(iv_Poster);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle("Details");

    }
}
