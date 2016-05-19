package movie.test.movies.presenter;

import java.util.List;

import movie.test.movies.model.SideMenu;
import movie.test.movies.model.SideMenuData;

/**
 * Created by muhammad ali
 * on 13/05/2016.
 */
public class SideMenuFragmentPresenter implements Observer {

    ViewRender view;
    SideMenuData menuData = new SideMenuData();

    public void initialize(ViewRender viewRender) {
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
//      System.out.println("title" +  ((SideMenu)object).getTitle());

    }

    @Override
    public void update(Object object) {

    }


    public interface ViewRender {
        void renderList(List<SideMenu> sideMenus);

    }


}
