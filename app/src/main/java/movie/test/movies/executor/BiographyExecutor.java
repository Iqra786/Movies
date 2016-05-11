package movie.test.movies.executor;


import android.widget.TextView;

import movie.test.movies.Interactor.Biography;
import movie.test.movies.repository.BiographyInteractorsResponse;


/**
 * Created by muhammad ali
 * on 11/05/2016.
 */
public class BiographyExecutor {

    private final Biography biography;

    public BiographyExecutor(BiographyInteractorsResponse biographyInteractorsResponse) {
        biography = new Biography(biographyInteractorsResponse);
    }

    /**
     *
     * @param textView - Text view to display data
     * @param position - Adaptor Position
     * @param id - Person Id
     */
    public void submitJobs(TextView textView, int position, int id) {
        biography.AddInQueue(textView, position, id);
    }
}
