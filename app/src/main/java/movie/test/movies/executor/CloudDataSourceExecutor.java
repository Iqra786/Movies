package movie.test.movies.executor;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import movie.test.movies.Interactor.CloudDataSource;
import movie.test.movies.R;
import movie.test.movies.model.Query;
import movie.test.movies.repository.Response;
import movie.test.movies.repository.InteractorResponse;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class CloudDataSourceExecutor extends DialogFragment implements Response {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String url = bundle.getString("url");
            int value = getArguments().getInt("value");
            CloudDataSource cloudDataSource = new CloudDataSource(url, value, this);
            cloudDataSource.execute();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment, container, false);
    }



    @Override
    public void onSuccess(Query response) {
        if (getTargetFragment() != null) {
            ((InteractorResponse) getTargetFragment()).upDateView(response);
        }

    }

    @Override
    public void onFailure() {

    }

}
