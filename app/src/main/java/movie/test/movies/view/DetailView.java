package movie.test.movies.view;

/**
 * Created by muhammad ali
 * on 17/05/2016.
 */

public interface DetailView {
    void setActivityTitle(String activityTitle);
    void setPosterImage(String imageName);
    void setTitle(String title);
    void setRating(double rating);
    void setReleaseYear(String year);
    void setInfo(String info);
    void setVote(int vote);
}
