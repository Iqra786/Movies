package movie.test.movies.data_manager;


import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import movie.test.movies.Interactor.CloudDataSource;
import movie.test.movies.model.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by muhammad ali
 * on 16/05/2016.
 */
public class DataManager {

    public void rxJavaCall(final String url, final int apiCall, final Response response) throws IOException, JSONException {

        final Observable<Integer> obersable = Observable.just(1);
        obersable.map(new Func1<Integer, JSONObject>() {
            @Override
            public JSONObject call(Integer integer) {
                JSONObject jsonObject = null;
                try {
                    jsonObject   = CloudDataSource.getJSONObjectFromURL(url);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonObject;
            }
        }).map(new Func1<JSONObject, Query>() {
            @Override
            public Query call(JSONObject jsonObject) {
                return CloudDataSource.parseJsonResponse(jsonObject, apiCall);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Query>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Query query) {
                if (response != null) {
                    response.dataManagerResponse(query);
                }
            }
        });


/*
        final Observable<JSONObject> myObersable2 = Observable.just(CloudDataSource.getJSONObjectFromURL(url));
        myObersable2.map(new Func1<JSONObject, Query>() {
            @Override
            public Query call(JSONObject jsonObject) {
                return CloudDataSource.test(jsonObject, apiCall);
            }
        }).subscribeOn(Schedulers.newThread()) // subscribeOn the I/O thread
                .subscribe(new Subscriber<Query>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Query query) {
                        if (response != null) {
                            response.dataManagerResponse(query);
                        }
                    }
                });*/


        /*Observable<Query> test = Observable.create(new Observable.OnSubscribe<Query>() {
            @Override
            public void call(Subscriber<? super Query> subscriber) {
                subscriber.onCompleted();
            }
        });

        test.subscribe((test)*/


        /*final Observable<Query> myObersable = Observable.create(new Observable.OnSubscribe<Query>() {
            @Override
            public void call(Subscriber<? super Query> subscriber) {
                try {
                    subscriber.onNext(CloudDataSource.test(url, apiCall));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) // subscribeOn the I/O thread
                .observeOn(AndroidSchedulers.mainThread());


        myObersable.subscribe(new Subscriber<Query>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onNext(Query result) {
                if (response != null) {
                    response.dataManagerResponse(result);
                }
            }
        });*/

    }

    public void biographyRequest() {

    }

    public interface Response {
        void dataManagerResponse(Query result);
    }


}
