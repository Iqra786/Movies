package movie.test.movies.presenter;

import movie.test.movies.data_manager.DataManager;
import movie.test.movies.model.Query;
import movie.test.movies.model.SideMenu;

/**
 * Created by muhammad ali
 * on 13/05/2016.
 */
public class MainPagePresenter implements Observer, DataManager.Response {

    private MainPageView mainPageView;

    public MainPagePresenter(MainPageView mainPageView) {
        this.mainPageView = mainPageView;
        Subject.getInstance().attach(this);
    }

    @Override
    public void update(Object object) {
        mainPageView.setNavigationBarTitle(((SideMenu) object).getTitle());
    }


    @Override
    public void dataManagerResponse(Query result) {
        System.out.println("Hello" + result);
    }

    public interface MainPageView {
        void setNavigationBarTitle(String title);

        void showProgress();

        void dismissProgress();
    }


}
