package hu.marton.tamas.movie.api.Configuration.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class Images {

    @SerializedName("base_url")
    private String baseUrl;
    @SerializedName("secure_base_url")
    private String secureBaseUrl;
    @SerializedName("backdrop_sizes")
    private List<String> backdropSizes = new ArrayList<String>();
    @SerializedName("logo_sizes")
    private List<String> logoSizes = new ArrayList<String>();
    @SerializedName("poster_sizes")
    private List<String> posterSizes = new ArrayList<String>();
    @SerializedName("profile_sizes")
    private List<String> profileSizes = new ArrayList<String>();
    @SerializedName("still_sizes")
    private List<String> stillSizes = new ArrayList<String>();

    /**
     * @return The baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl The base_url
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return The secureBaseUrl
     */
    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    /**
     * @param secureBaseUrl The secure_base_url
     */
    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    /**
     * @return The backdropSizes
     */
    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    /**
     * @param backdropSizes The backdrop_sizes
     */
    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    /**
     * @return The logoSizes
     */
    public List<String> getLogoSizes() {
        return logoSizes;
    }

    /**
     * @param logoSizes The logo_sizes
     */
    public void setLogoSizes(List<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    /**
     * @return The posterSizes
     */
    public List<String> getPosterSizes() {
        return posterSizes;
    }

    /**
     * @param posterSizes The poster_sizes
     */
    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    /**
     * @return The profileSizes
     */
    public List<String> getProfileSizes() {
        return profileSizes;
    }

    /**
     * @param profileSizes The profile_sizes
     */
    public void setProfileSizes(List<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    /**
     * @return The stillSizes
     */
    public List<String> getStillSizes() {
        return stillSizes;
    }

    /**
     * @param stillSizes The still_sizes
     */
    public void setStillSizes(List<String> stillSizes) {
        this.stillSizes = stillSizes;
    }
}
