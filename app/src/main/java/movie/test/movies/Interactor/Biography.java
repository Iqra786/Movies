package movie.test.movies.Interactor;

import android.os.Handler;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import movie.test.movies.repository.BiographyInteractorsResponse;


/**
 * GET and load Biography
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class Biography {

    private final ExecutorService mExecutorService;
    private static final Handler handler = new Handler();
    private static BiographyInteractorsResponse mBiographyInteractorsResponse;


    public Biography(BiographyInteractorsResponse biographyInteractorsResponse) {
        mExecutorService = Executors.newFixedThreadPool(10);
        mBiographyInteractorsResponse = biographyInteractorsResponse;
    }

    public void AddInQueue(TextView textView, int position, int id) {
        String endPoint = String.format("https://api.themoviedb.org/3/person/%s?api_key=0a08e38b874d0aa2d426ffc04357069d&append_to_response=Primary", String.valueOf(id));
        BiographyApi biographyApi = new BiographyApi(textView, position, endPoint);
        mExecutorService.submit(biographyApi);
    }


    private static class BiographyApi implements Runnable {
        private final TextView tv_Biography;
        private final int position;
        private final String url;

        public BiographyApi(TextView tv_Biography, int position, String url) {
            this.tv_Biography = tv_Biography;
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
                    if (biography != null) {
                        UpdateView updateView = new UpdateView(tv_Biography, position, biography);
                        handler.post(updateView);
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static class UpdateView implements Runnable {

        private final TextView tv_Biography;
        private final int position;
        private final String bio_Text;


        public UpdateView(TextView tv_Biography, int position, String bio_Text) {
            this.tv_Biography = tv_Biography;
            this.position = position;
            this.bio_Text = bio_Text;

        }

        @Override
        public void run() {
            tv_Biography.setText(bio_Text);
            if (mBiographyInteractorsResponse  != null) {
                mBiographyInteractorsResponse .updateAdaptor(position, bio_Text);
            }
        }
    }

}
