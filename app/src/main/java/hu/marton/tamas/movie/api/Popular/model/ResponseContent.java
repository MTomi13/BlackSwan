package hu.marton.tamas.movie.api.Popular.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class ResponseContent {

    private Integer page;
    private List<ResultWrapper> results = new ArrayList<ResultWrapper>();
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("total_results")
    private Integer totalResults;

    private ContentType contentType;

    /**
     * @return The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return The resultWrappers
     */
    public List<ResultWrapper> getResultWrappers() {
        return results;
    }

    /**
     * @param resultWrappers The resultWrappers
     */
    public void setResultWrappers(List<ResultWrapper> resultWrappers) {
        this.results = resultWrappers;
    }

    /**
     * @return The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }


    /**
     * @return The contentType
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType The content type
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
