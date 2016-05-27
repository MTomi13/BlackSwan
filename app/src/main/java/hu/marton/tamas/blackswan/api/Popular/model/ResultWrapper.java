package hu.marton.tamas.blackswan.api.Popular.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class ResultWrapper implements Parcelable {

    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    private Integer id;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    private String originalName;
    private String overview;
    @SerializedName("origin_country")
    private List<String> originCountry = new ArrayList<String>();
    @SerializedName("poster_path")
    private String posterPath;
    private Double popularity;
    @SerializedName("name")
    private String name;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("release_date")
    private String releaseDate;
    private String title;
    @SerializedName("profile_path")
    private String profilePath;

    @SerializedName("original_title")
    private String originalTitle;

    private String logoImageUrl;
    private String profileImageUrl;
    private String backDropImageUrl;

    private ContentType contentType;

    /**
     * @return The backdropPath
     */
    public String getBackdropPath() {
        return backdropPath;
    }

    /**
     * @param backdropPath The backdrop_path
     */
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    /**
     * @return The firstAirDate
     */
    public String getFirstAirDate() {
        return firstAirDate;
    }

    /**
     * @param firstAirDate The first_air_date
     */
    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    /**
     * @return The genreIds
     */
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    /**
     * @param genreIds The genre_ids
     */
    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The originalLanguage
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * @param originalLanguage The original_language
     */
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    /**
     * @return The originalName
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * @param originalName The original_name
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * @return The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * @return The originCountry
     */
    public List<String> getOriginCountry() {
        return originCountry;
    }

    /**
     * @param originCountry The origin_country
     */
    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * @param posterPath The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    /**
     * @return The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     * @param popularity The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The voteAverage
     */
    public Double getVoteAverage() {
        return voteAverage;
    }

    /**
     * @param voteAverage The vote_average
     */
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    /**
     * @return The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     * @param voteCount The vote_count
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    /**
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return The logo image url
     */
    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    /**
     * @param logoImageUrl The logo image url
     */
    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The profilePath
     */
    public String getProfilePath() {
        return profilePath;
    }

    /**
     * @param profilePath The profile_path
     */
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    /**
     * @return The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * @param originalTitle The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * @return The profileImageUrl
     */
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    /**
     * @param profileImageUrl The profile image url
     */
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    /**
     * @return The backDropImageUrl
     */
    public String getBackDropImageUrl() {
        return backDropImageUrl;
    }

    /**
     * @param backDropImageUrl The back drop image url
     */
    public void setBackDropImageUrl(String backDropImageUrl) {
        this.backDropImageUrl = backDropImageUrl;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdropPath);
        dest.writeString(this.firstAirDate);
        dest.writeList(this.genreIds);
        dest.writeValue(this.id);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalName);
        dest.writeString(this.overview);
        dest.writeStringList(this.originCountry);
        dest.writeString(this.posterPath);
        dest.writeValue(this.popularity);
        dest.writeString(this.name);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
        dest.writeString(this.releaseDate);
        dest.writeString(this.title);
        dest.writeString(this.profilePath);
        dest.writeString(this.originalTitle);
        dest.writeString(this.logoImageUrl);
        dest.writeString(this.profileImageUrl);
        dest.writeString(this.backDropImageUrl);
        dest.writeInt(this.contentType == null ? -1 : this.contentType.ordinal());
    }

    public ResultWrapper() {
    }

    protected ResultWrapper(Parcel in) {
        this.backdropPath = in.readString();
        this.firstAirDate = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalLanguage = in.readString();
        this.originalName = in.readString();
        this.overview = in.readString();
        this.originCountry = in.createStringArrayList();
        this.posterPath = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.name = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.releaseDate = in.readString();
        this.title = in.readString();
        this.profilePath = in.readString();
        this.originalTitle = in.readString();
        this.logoImageUrl = in.readString();
        this.profileImageUrl = in.readString();
        this.backDropImageUrl = in.readString();
        int tmpContentType = in.readInt();
        this.contentType = tmpContentType == -1 ? null : ContentType.values()[tmpContentType];
    }

    public static final Parcelable.Creator<ResultWrapper> CREATOR = new Parcelable.Creator<ResultWrapper>() {
        @Override
        public ResultWrapper createFromParcel(Parcel source) {
            return new ResultWrapper(source);
        }

        @Override
        public ResultWrapper[] newArray(int size) {
            return new ResultWrapper[size];
        }
    };
}
