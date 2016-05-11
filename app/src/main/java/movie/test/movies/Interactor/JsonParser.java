package movie.test.movies.Interactor;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import movie.test.movies.model.Movie;
import movie.test.movies.model.Person;
import movie.test.movies.model.Query;
import movie.test.movies.model.Tv;

/**
 * Created by muhammad ali
 * on 09/05/2016.
 */
public class JsonParser {

    private static final String TAG = JsonParser.class.getSimpleName();


    public Query mapMovieResult(JSONObject result) {
        Query query = null;
        List<Movie> movie_resultList = null;
        if (result != null) {
            query = new Query();
            try {
                if (result.has("page")) {
                    int page = result.getInt("page");
                    query.setPage(page);
                }
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                if (result.has("results") && result.getJSONArray("results").length() > 0) {
                    JSONArray resultsArray = result.getJSONArray("results");
                    Movie movie_result;
                    movie_resultList = new ArrayList<>();
                    for (int i = 0; i <= resultsArray.length(); i++) {
                        JSONObject movie_DetailObject = resultsArray.getJSONObject(i);
                        movie_result = new Movie();
                        try {
                            String poster_Path = movie_DetailObject.has("poster_path") ? movie_DetailObject.getString("poster_path") : null;
                            movie_result.setPosterPath(poster_Path);
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            String release_Date = movie_DetailObject.has("release_date") ? movie_DetailObject.getString("release_date") : null;
                            movie_result.setReleaseDate(release_Date);
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("adult")) {
                                boolean adult = movie_DetailObject.getBoolean("adult");
                                movie_result.setAdult(adult);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }


                        try {
                            if (movie_DetailObject.has("overview")) {
                                String overview = movie_DetailObject.getString("overview");
                                movie_result.setOverview(overview);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("id")) {
                                int id = movie_DetailObject.getInt("id");
                                movie_result.setId(id);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("original_title")) {
                                String original_title = movie_DetailObject.getString("original_title");
                                movie_result.setOriginalTitle(original_title);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("original_language")) {
                                String original_language = movie_DetailObject.getString("original_language");
                                movie_result.setOriginalLanguage(original_language);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("title")) {
                                String title = movie_DetailObject.getString("title");
                                movie_result.setTitle(title);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }


                        try {
                            if (movie_DetailObject.has("popularity")) {
                                Double popularity = movie_DetailObject.getDouble("popularity");
                                movie_result.setPopularity(popularity);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("vote_count")) {
                                int vote_count = movie_DetailObject.getInt("vote_count");
                                movie_result.setVoteCount(vote_count);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("video")) {
                                boolean video = movie_DetailObject.getBoolean("video");
                                movie_result.setVideo(video);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("vote_average")) {
                                Double vote_average = movie_DetailObject.getDouble("vote_average");
                                movie_result.setVoteAverage(vote_average);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        movie_resultList.add(movie_result);
                    }
                }

            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        if (query != null) {
            query.setResults(movie_resultList);
        }
        return query;
    }


    public Query mapTvResult(JSONObject result) {
        Query query = null;
        List<Tv> tv_ResultList = null;
        if (result != null) {
            query = new Query();
            try {
                if (result.has("page")) {
                    int page = result.getInt("page");
                    query.setPage(page);
                }
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                if (result.has("results") && result.getJSONArray("results").length() > 0) {
                    JSONArray resultsArray = result.getJSONArray("results");
                    Tv tv_Result;
                    tv_ResultList = new ArrayList<>();
                    for (int i = 0; i <= resultsArray.length(); i++) {
                        JSONObject movie_DetailObject = resultsArray.getJSONObject(i);
                        tv_Result = new Tv();
                        try {
                            if (movie_DetailObject.has("poster_path")) {
                                String poster_Path = movie_DetailObject.getString("poster_path");
                                tv_Result.setPosterPath(poster_Path);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("first_air_date")) {
                                String release_Date = movie_DetailObject.getString("first_air_date");
                                tv_Result.setReleaseDate(release_Date);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }


                        try {
                            if (movie_DetailObject.has("overview")) {
                                String overview = movie_DetailObject.getString("overview");
                                tv_Result.setOverview(overview);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("id")) {
                                int id = movie_DetailObject.getInt("id");
                                tv_Result.setId(id);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("original_name")) {
                                String original_Name = movie_DetailObject.getString("original_name");
                                tv_Result.setOriginalTitle(original_Name);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("original_language")) {
                                String original_language = movie_DetailObject.getString("original_language");
                                tv_Result.setOriginalLanguage(original_language);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("name")) {
                                String name = movie_DetailObject.getString("name");
                                tv_Result.setTitle(name);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }


                        try {
                            if (movie_DetailObject.has("popularity")) {
                                Double popularity = movie_DetailObject.getDouble("popularity");
                                tv_Result.setPopularity(popularity);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("vote_count")) {
                                int vote_count = movie_DetailObject.getInt("vote_count");
                                tv_Result.setVoteCount(vote_count);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                        try {
                            if (movie_DetailObject.has("vote_average")) {
                                Double vote_average = movie_DetailObject.getDouble("vote_average");
                                tv_Result.setVoteAverage(vote_average);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        tv_ResultList.add(tv_Result);
                    }
                }

            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        if (query != null) {
            query.setResults(tv_ResultList);
        }
        return query;
    }


    public Query mapPeople(JSONObject result) {
        Query query = null;
        List<Person> pp_ResultList = null;
        if (result != null) {
            query = new Query();
            try {
                if (result.has("page")) {
                    int page = result.getInt("page");
                    query.setPage(page);
                }
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                if (result.has("results") && result.getJSONArray("results").length() > 0) {
                    JSONArray resultsArray = result.getJSONArray("results");
                    Person pp_Result;
                    pp_ResultList = new ArrayList<>();
                    for (int i = 0; i <= resultsArray.length(); i++) {
                        JSONObject movie_DetailObject = resultsArray.getJSONObject(i);
                        pp_Result = new Person();
                        try {
                            if (movie_DetailObject.has("profile_path")) {
                                String profile_Path = movie_DetailObject.getString("profile_path");
                                pp_Result.setPosterPath(profile_Path);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("name")) {
                                String name = movie_DetailObject.getString("name");
                                pp_Result.setTitle(name);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        try {
                            if (movie_DetailObject.has("id")) {
                                int id = movie_DetailObject.getInt("id");
                                pp_Result.setId(id);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        pp_ResultList.add(pp_Result);
                    }
                }

            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }

        if (query != null) {
            query.setResults(pp_ResultList);
        }
        return query;
    }


    public String getBiography(JSONObject result) {
        String biography = null;

        if (result != null) {

            try {
                if (result.has("biography")) {
                    biography = result.getString("biography");
                }
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }

        return biography;
    }


}
