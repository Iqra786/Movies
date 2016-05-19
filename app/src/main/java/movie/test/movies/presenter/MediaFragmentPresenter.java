package movie.test.movies.presenter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import movie.test.movies.data_manager.DataManager;
import movie.test.movies.model.Query;
import movie.test.movies.model.SideMenu;
import movie.test.movies.view.MediaResultView;

/**
 * Created by muhammad ali
 * on 17/05/2016.
 */
public class MediaFragmentPresenter implements Observer, DataManager.Response {

    MediaResultView mMediaResult;

    public MediaFragmentPresenter(MediaResultView mediaResult) {
        Subject.getInstance().attach(this);
        this.mMediaResult =mediaResult;
    }


    public void onClassLoad() {
        if (mMediaResult != null) {
            mMediaResult.showProgress();
        }
        DataManager dataManager = new DataManager();
        try {
            dataManager.rxJavaCall("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=0a08e38b874d0aa2d426ffc04357069d", 0, this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {
        DataManager dataManager = new DataManager();
        try {
            dataManager.rxJavaCall(((SideMenu) object).getUrl(), ((SideMenu) object).getId(), this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mMediaResult != null) {
            mMediaResult.showProgress();
        }
    }

    @Override
    public void dataManagerResponse(Query result) {
        if (result != null) {
            List<?> data = result.getResults();
            if (mMediaResult != null) {
                mMediaResult.renderList(data);
            }
        }
    }



}
