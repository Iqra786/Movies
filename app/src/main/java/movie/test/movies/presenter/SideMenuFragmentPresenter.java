package movie.test.movies.presenter;

import movie.test.movies.model.SideMenuData;
import movie.test.movies.view.SideMenuView;

/**
 * Created by muhammad ali
 * on 13/05/2016.
 */
public class SideMenuFragmentPresenter implements Observer {

    SideMenuView view;
    SideMenuData menuData = new SideMenuData();

    public void initialize(SideMenuView viewRender) {
        this.view = viewRender;
        loadMenuList();
        Subject.getInstance().attach(this);
    }

    private void loadMenuList() {
        if (view != null) {
            view.renderList(menuData.getSideMenus());
        }
    }

    public void setTitle(Object object) {
      Subject.getInstance().notifyAllObserver(object);
    }

    @Override
    public void update(Object object) {

    }



}
