package movie.test.movies.Interactor;

import android.os.Handler;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import movie.test.movies.presenter.MediaAdapterModel;


/**
 * GET and load Biography
 * Created by muhammad ali
 * on 10/05/2016.
 */
public  class Biography {

    private final ExecutorService mExecutorService;
    private static final Handler handler = new Handler();
    private static MediaAdapterModel mMediaAdapterModel;

    private static Biography ourInstance;

    public static Biography getInstance(MediaAdapterModel mediaAdapterModel) {
        mMediaAdapterModel = mediaAdapterModel;
        return   ourInstance == null ? new Biography() : ourInstance;
    }


    private Biography() {
        mExecutorService = Executors.newFixedThreadPool(10);
    }



    public void AddInQueue(int position, int id) {
        String endPoint = String.format("https://api.themoviedb.org/3/person/%s?api_key=0a08e38b874d0aa2d426ffc04357069d&append_to_response=Primary", String.valueOf(id));
        BiographyApi biographyApi = new BiographyApi(position, endPoint);
        mExecutorService.submit(biographyApi);
    }


    private static class BiographyApi implements Runnable {
        private final int position;
        private final String url;

        public BiographyApi( int position, String url) {
            this.position = position;
            this.url = url;
        }

        @Override
        public void run() {
            try {
                JSONObject jsonObject = CloudDataSource.getJSONObjectFromURL(url);
                JsonParser jsonParser = new JsonParser();
                if (jsonObject != null) {
                    String biography = jsonParser.getBiography(jsonObject);
                    System.out.println("biography text" + biography);
                    if (mMediaAdapterModel != null) {
                        mMediaAdapterModel.setBiographyAtIndex(biography , position);
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }



}
