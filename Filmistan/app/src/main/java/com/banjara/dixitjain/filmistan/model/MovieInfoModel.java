package com.banjara.dixitjain.filmistan.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieInfoModel implements Serializable {


    @SerializedName("budget")
    @Expose
    private Integer budget;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompany> productionCompanies = null;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("revenue")
    @Expose
    private Integer revenue;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("credits")
    @Expose
    private Credits credits;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;


    public Integer getBudget() {
        return budget;
    }


    public String getOverview() {
        return overview;
    }

    public Double getPopularity() {
        return popularity;
    }


    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }


    public Integer getRuntime() {
        return runtime;
    }


    public String getTitle() {
        return title;
    }


    public Double getVoteAverage() {
        return voteAverage;
    }


    public Integer getVoteCount() {
        return voteCount;
    }


    public Credits getCredits() {
        return credits;
    }

    public String getPosterPath() {
        return posterPath;
    }


}
