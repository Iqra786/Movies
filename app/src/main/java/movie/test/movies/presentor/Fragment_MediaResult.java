package movie.test.movies.presentor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import movie.test.movies.executor.CloudDataSourceExecutor;
import movie.test.movies.R;
import movie.test.movies.model.Query;
import movie.test.movies.repository.InteractorResponse;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class Fragment_MediaResult extends Fragment implements InteractorResponse {

    private CloudDataSourceExecutor mUrlRequest;
    private MediaAdaptor mAdaptor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_api_result, container, false);

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.main_RecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdaptor = new MediaAdaptor();
        mRecyclerView.setAdapter(mAdaptor);
        mUrlRequest = (CloudDataSourceExecutor) getFragmentManager().findFragmentByTag("ApiRequest");
        if (mUrlRequest == null) {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=0a08e38b874d0aa2d426ffc04357069d");
            bundle.putInt("value", 0);
            apiRequest(bundle);
        }

        return v;
    }


    public void ApiDecision(int value) {
        Bundle bundle = new Bundle();
        switch (value) {
            case 0:
                bundle.putInt("value", 0);
                bundle.putString("url", "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=0a08e38b874d0aa2d426ffc04357069d");
                apiRequest(bundle);
                break;
            case 1:
                bundle.putInt("value", 1);
                bundle.putString("url", "https://api.themoviedb.org/3/discover/tv?sort_by=popularity.desc&api_key=0a08e38b874d0aa2d426ffc04357069d");
                apiRequest(bundle);
                break;
            case 2:
                bundle.putInt("value", 2);
                bundle.putString("url", "https://api.themoviedb.org/3/person/popular?api_key=0a08e38b874d0aa2d426ffc04357069d");
                apiRequest(bundle);
                break;
        }
    }

    private void apiRequest(Bundle bundle) {
        if (mUrlRequest == null) {
            mUrlRequest = new CloudDataSourceExecutor();
            mUrlRequest.setArguments(bundle);
            mUrlRequest.setTargetFragment(this, 44);
            mUrlRequest.show(getFragmentManager(), "ApiRequest");

        }

    }

    @Override
    public void upDateView(Query response) {
        if (response != null) {
            List<?> data = response.getResults();
            mAdaptor.clearAdaptor();
            mAdaptor.setAdaptor(data);
        }

        if (mUrlRequest != null) {
            mUrlRequest.dismiss();
            mUrlRequest = null;
        }
    }
}
