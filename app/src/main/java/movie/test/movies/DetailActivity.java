package movie.test.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import movie.test.movies.model.Detail_Info;
import movie.test.movies.presenter.DetailPresenter;
import movie.test.movies.view.DetailView;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class DetailActivity extends AppCompatActivity implements DetailView {

    private TextView tv_Title;
    private TextView tv_Rating;
    private TextView tv_Year;
    private TextView tv_Info;
    private TextView tv_Votes;
    private ImageView iv_Poster;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailPresenter mDetailPresenter = new DetailPresenter(this);
        tv_Title = (TextView) findViewById(R.id.tv_Title);
        tv_Rating = (TextView) findViewById(R.id.tv_Rating);
        tv_Year = (TextView) findViewById(R.id.tv_ReleaseYear);
        tv_Info = (TextView) findViewById(R.id.tv_Info);
        tv_Votes = (TextView) findViewById(R.id.tv_Votes);
        iv_Poster = (ImageView) findViewById(R.id.iv_Poster);
        Detail_Info info = (Detail_Info) getIntent().getSerializableExtra("data");
        mDetailPresenter.getData(info);
    }


    @Override
    public void setTitle(CharSequence title) {


    }

    @Override
    public void setActivityTitle(String activityTitle) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(activityTitle);
        }
    }

    @Override
    public void setPosterImage(String imageName) {
        if (iv_Poster != null) {
            String url = String.format("http://image.tmdb.org/t/p/w185%s", imageName);
            Picasso.with(iv_Poster.getContext()).load(url).into(iv_Poster);
        }

    }

    @Override
    public void setTitle(String title) {
        if (tv_Title != null) {
            tv_Title.setText(title);
        }
    }

    @Override
    public void setRating(double rating) {
        if (tv_Rating != null) {
            String a_Vote = String.valueOf(rating) + "/ 10";
            tv_Rating.setText(a_Vote);
        }
    }

    @Override
    public void setReleaseYear(String year) {
        if (tv_Year != null) {
            tv_Year.setText(year);
        }
    }

    @Override
    public void setInfo(String info) {
        if (tv_Info != null) {
            tv_Info.setText(info);
        }
    }

    @Override
    public void setVote(int vote) {
        System.out.println("setVote" + vote);
        if (tv_Votes != null) {
            tv_Votes.setText(String.valueOf(vote));
        }
    }
}
