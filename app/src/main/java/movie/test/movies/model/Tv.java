package movie.test.movies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad ali
 * on 10/05/2016.
 */
public class Tv extends Detail_Info implements Serializable {

    private List<String> originCountry = new ArrayList<>();

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }
}
