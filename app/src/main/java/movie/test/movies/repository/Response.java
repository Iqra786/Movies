package movie.test.movies.repository;

import movie.test.movies.model.Query;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public interface Response {

   void onSuccess(Query response);
    void onFailure();



}
