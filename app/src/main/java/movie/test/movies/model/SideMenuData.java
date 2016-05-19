package movie.test.movies.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by muhammad ali
 * on 13/05/2016.
 */
public class SideMenuData {

    private static final List<SideMenu>  sideMenus = Arrays.asList(new SideMenu(0 , "Movies" , "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=0a08e38b874d0aa2d426ffc04357069d") , new SideMenu(1 , "TV Shows" , "https://api.themoviedb.org/3/discover/tv?sort_by=popularity.desc&api_key=0a08e38b874d0aa2d426ffc04357069d") , new SideMenu(2, "Person" , "https://api.themoviedb.org/3/person/popular?api_key=0a08e38b874d0aa2d426ffc04357069d"));

    public  List<SideMenu> getSideMenus() {
        return sideMenus;
    }
}
