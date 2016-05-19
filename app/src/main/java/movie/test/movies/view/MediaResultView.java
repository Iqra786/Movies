package movie.test.movies.view;

import java.util.List;

/**
 * Created by muhammadali
 * on 19/05/2016.
 */
public interface MediaResultView {
    void renderList(List<?> data);

    void showProgress();

    void dismissProgress();
}
