package movie.test.movies.presenter;

import movie.test.movies.model.Detail_Info;
import movie.test.movies.view.DetailView;

/**
 * Created by muhammad ali
 * on 17/05/2016.
 */
public class DetailPresenter {

    private DetailView mDetailView;

    public DetailPresenter(DetailView mDetailView) {
        this.mDetailView = mDetailView;
    }

    public void getData(Detail_Info info) {
        if (mDetailView != null) {
            mDetailView.setActivityTitle("Detail");
            mDetailView.setInfo(info.getOverview());
            mDetailView.setPosterImage(info.getPosterPath());
            mDetailView.setRating(info.getVoteAverage());
            mDetailView.setReleaseYear(info.getReleaseDate());
            mDetailView.setTitle(info.getTitle());
            mDetailView.setVote(info.getVoteCount());
        }
    }
}
