package hu.marton.tamas.movie.api.Configuration.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class Configuration {

    private Images images;
    @SerializedName("change_keys")
    private List<String> changeKeys = new ArrayList<String>();

    /**
     *
     * @return
     * The images
     */
    public Images getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     *
     * @return
     * The changeKeys
     */
    public List<String> getChangeKeys() {
        return changeKeys;
    }

    /**
     *
     * @param changeKeys
     * The change_keys
     */
    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }
}
