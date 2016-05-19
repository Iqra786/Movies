package movie.test.movies.Interactor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import movie.test.movies.model.Query;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class CloudDataSource {

    public static Query test(String url , int apiCall) throws IOException, JSONException {
        Query query = new Query();
        JSONObject jsonObject = getJSONObjectFromURL(url);
        JsonParser jsonParser = new JsonParser();
        if (jsonObject != null) {
            switch (apiCall) {
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
        return query;
    }

    public static Query parseJsonResponse(JSONObject jsonObject, int apiCall) {
        Query query = new Query();
        JsonParser jsonParser = new JsonParser();
        if (jsonObject != null) {
            switch (apiCall) {
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
        return query;
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
        return new JSONObject(jsonString);
    }
}
