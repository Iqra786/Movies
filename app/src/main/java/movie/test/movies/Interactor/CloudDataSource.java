package movie.test.movies.Interactor;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import movie.test.movies.model.Query;
import movie.test.movies.repository.Response;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class CloudDataSource extends AsyncTask<Void, Void, Query> {

    private String mUrl;
    private int mApiCall;
    private Response mResponse;

    public CloudDataSource(String url, int apiCall, Response response) {
        mUrl = url;
        mApiCall = apiCall;
        mResponse = response;
    }

    @Override
    protected Query doInBackground(Void... params) {
        Query query = new Query();
        try {
            JSONObject jsonObject = getJSONObjectFromURL(mUrl);
            JsonParser jsonParser = new JsonParser();
            if (jsonObject != null) {
                switch (mApiCall) {
                    case 0:
                        query = jsonParser.mapMovieResult(jsonObject);
                        break;
                    case 1:
                        query = jsonParser.mapTvResult(jsonObject);
                        break;
                    case 2:
                        query = jsonParser.mapPeople(jsonObject);
                        break;
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            if (mResponse != null) {
                mResponse.onFailure();
            }
        }
        return query;
    }

    @Override
    protected void onPostExecute(Query query) {
        super.onPostExecute(query);
        if (mResponse != null) {
            mResponse.onSuccess(query);
        }
    }


    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        String jsonString;

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        urlConnection.disconnect();
        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }
}
