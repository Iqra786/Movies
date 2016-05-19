package movie.test.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import java.util.List;
import movie.test.movies.model.SideMenu;
import movie.test.movies.presenter.SideMenuFragmentPresenter;
import movie.test.movies.view.SideMenuView;

/**
 * Created by muhammad ali
 * on 13/05/2016.
 */
public class SideMenuFragment extends ListFragment implements SideMenuView {

    private static final String TAG = SideMenuFragment.class.getSimpleName();
    private ListView mListView;
    private SideMenuFragmentPresenter mSideMenuFragmentPresenter;
    private MenuAdaptor mMenuAdaptor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_side_menu, container, false);
        mListView = (ListView) view.findViewById(android.R.id.list);
        mMenuAdaptor = new MenuAdaptor(getContext().getApplicationContext() , R.layout.drawer_list_item);
        if (mListView != null) {

            mListView.setAdapter(mMenuAdaptor);
        }

        mSideMenuFragmentPresenter = new SideMenuFragmentPresenter();
        mSideMenuFragmentPresenter.initialize(this);
        return view;
    }

    @Override
    public void renderList(List<SideMenu> sideMenus) {
        mMenuAdaptor.addInSideMenuList(sideMenus);
    }




    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
         mSideMenuFragmentPresenter.setTitle(mMenuAdaptor.getItem(position));
    }
}
