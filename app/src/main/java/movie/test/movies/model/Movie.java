package movie.test.movies.model;

import java.io.Serializable;

/**
 * Created by muhammad ali on 10/05/2016.
 */
public class Movie extends MediaInfo implements Serializable {

    private Boolean adult;
    private Boolean video;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }
}
