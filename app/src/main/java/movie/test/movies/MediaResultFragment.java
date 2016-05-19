package movie.test.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import java.util.List;

import movie.test.movies.presenter.MediaFragmentPresenter;
import movie.test.movies.view.MediaResultView;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class MediaResultFragment extends Fragment implements MediaResultView {

    private static final int POSITION_LIST = 0;
    private static final int POSITION_LOADING = 1;
    private static final int POSITION_EMPTY = 2;


    private MediaAdaptor mAdaptor;
    private ViewAnimator mAnimator;
    private MediaFragmentPresenter mMediaFragmentPresenter;
    private RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_api_result, container, false);

        mAnimator = (ViewAnimator) v.findViewById(R.id.animator);
        mMediaFragmentPresenter = new MediaFragmentPresenter(this);

        mRecyclerView = (RecyclerView) mAnimator.getChildAt(POSITION_LIST);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdaptor = new MediaAdaptor();
        mRecyclerView.setAdapter(mAdaptor);
        mMediaFragmentPresenter.onClassLoad();
        return v;
    }





    @Override
    public void renderList(List<?> data) {
        if (data != null) {
            mAnimator.setDisplayedChild(POSITION_LIST);
            mAdaptor.clearAdaptor();
            mAdaptor.setAdaptor(data);
        }
    }

    @Override
    public void showProgress() {
        mAnimator.setDisplayedChild(POSITION_LOADING);

    }

    @Override
    public void dismissProgress() {
//        mAnimator.setDisplayedChild(POSITION_EMPTY);

    }
}
