package movie.test.movies.presentor;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import movie.test.movies.R;


public class Activity_LandingPage extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private final static String TAG = Activity_LandingPage.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            mListView = (ListView) findViewById(R.id.left_drawer);
            String[] array = getResources().getStringArray(R.array.movies_options);
            ArrayAdapter<String> listAdaptor = new ArrayAdapter<>(this, R.layout.drawer_list_item, array);
            if (mListView != null) {
                mListView.setOnItemClickListener(this);
                mListView.setAdapter(listAdaptor);
            }

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (getSupportActionBar() != null) {
                EditText editText = new EditText(this);
                getSupportActionBar().setCustomView(editText);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }

            mDrawerToggle = new ActionBarDrawerToggle
                    (this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    invalidateOptionsMenu();
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    invalidateOptionsMenu();
                }
            };
            mDrawerLayout.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.content_frame, new Fragment_MediaResult(), "Api_Result");
            ft.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_websearch:
                Log.i(TAG, "Websearch");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, String.valueOf(id));
        String title = (String) parent.getAdapter().getItem(position);
        setTitle(title);
        mDrawerLayout.closeDrawers();
        FragmentManager fm = getSupportFragmentManager();
        Fragment_MediaResult api_result = (Fragment_MediaResult) fm.findFragmentByTag("Api_Result");
        if (api_result != null) {
            api_result.ApiDecision(position);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mDrawerLayout != null) {
            boolean drawerOpen = mDrawerLayout.isDrawerOpen(mListView);
            menu.findItem(R.id.action_websearch).setVisible(drawerOpen);
        }
        return super.onPrepareOptionsMenu(menu);
    }

//    https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d/discover/movie?sort_by=popularity.desc

    // https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc?api_key=0a08e38b874d0aa2d426ffc04357069d

//    https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d&append_to_response=releases,trailers

//    http://image.tmdb.org/t/p/w185/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg"
}
