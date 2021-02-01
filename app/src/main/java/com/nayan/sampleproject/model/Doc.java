package com.nayan.sampleproject.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doc {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("journal")
    @Expose
    private String journal;
    @SerializedName("eissn")
    @Expose
    private String eissn;
    @SerializedName("publication_date")
    @Expose
    private String publicationDate;
    @SerializedName("article_type")
    @Expose
    private String articleType;
    @SerializedName("author_display")
    @Expose
    private List<String> authorDisplay = null;
    @SerializedName("abstract")
    @Expose
    private List<String> _abstract = null;
    @SerializedName("title_display")
    @Expose
    private String titleDisplay;
    @SerializedName("score")
    @Expose
    private Double score;

    public Doc(String s) {
        this.id=s;
    }

    public Doc() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getEissn() {
        return eissn;
    }

    public void setEissn(String eissn) {
        this.eissn = eissn;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public List<String> getAuthorDisplay() {
        return authorDisplay;
    }

    public void setAuthorDisplay(List<String> authorDisplay) {
        this.authorDisplay = authorDisplay;
    }

    public List<String> getAbstract() {
        return _abstract;
    }

    public void setAbstract(List<String> _abstract) {
        this._abstract = _abstract;
    }

    public String getTitleDisplay() {
        return titleDisplay;
    }

    public void setTitleDisplay(String titleDisplay) {
        this.titleDisplay = titleDisplay;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}