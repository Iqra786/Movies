package movie.test.movies.presenter;


import movie.test.movies.Interactor.Biography;

/**
 * Created by muhammad ali
 * on 19/05/2016.
 */
public class MediaAdaptorPresenter implements MediaAdapterModel {

    MediaAdapterModel mediaAdapterModel;


    public MediaAdaptorPresenter(MediaAdapterModel mediaAdapterModel) {
        this.mediaAdapterModel = mediaAdapterModel;
    }

    public void biographyRequest(int position, int id) {
        Biography.getInstance(this).AddInQueue(position, id);

    }


    @Override
    public void setBiographyAtIndex(String biography, int position) {
        mediaAdapterModel.setBiographyAtIndex(biography , position);
    }
}
