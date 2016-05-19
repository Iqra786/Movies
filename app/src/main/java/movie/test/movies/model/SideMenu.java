package movie.test.movies.model;

/**
 * Created by muhammad ali
 * on 13/05/2016.
 */
public class SideMenu {

    private int id;
    private String title;
    private String url;

    public SideMenu(int id, String title , String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
