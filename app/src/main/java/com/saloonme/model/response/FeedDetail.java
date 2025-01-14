package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FeedDetail implements Serializable
{

    @SerializedName("feed_sno")
    @Expose
    private String feedSno;
    @SerializedName("feed_name")
    @Expose
    private String feedName;
    @SerializedName("feed_link")
    @Expose
    private String feedLink;
    @SerializedName("feed_des")
    @Expose
    private String feedDes;
    @SerializedName("feed_img")
    @Expose
    private String feedImg;
    @SerializedName("feed_date_time")
    @Expose
    private String feedDateTime;
    @SerializedName("feed_user_id")
    @Expose
    private String feedUserId;
    @SerializedName("type_of_access")
    @Expose
    private String typeOfAccess;
    private final static long serialVersionUID = -2752494542509891735L;

    public String getFeedSno() {
        return feedSno;
    }

    public void setFeedSno(String feedSno) {
        this.feedSno = feedSno;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getFeedLink() {
        return feedLink;
    }

    public void setFeedLink(String feedLink) {
        this.feedLink = feedLink;
    }

    public String getFeedDes() {
        return feedDes;
    }

    public void setFeedDes(String feedDes) {
        this.feedDes = feedDes;
    }

    public String getFeedImg() {
        return feedImg;
    }

    public void setFeedImg(String feedImg) {
        this.feedImg = feedImg;
    }

    public String getFeedDateTime() {
        return feedDateTime;
    }

    public void setFeedDateTime(String feedDateTime) {
        this.feedDateTime = feedDateTime;
    }

    public String getFeedUserId() {
        return feedUserId;
    }

    public void setFeedUserId(String feedUserId) {
        this.feedUserId = feedUserId;
    }

    public String getTypeOfAccess() {
        return typeOfAccess;
    }

    public void setTypeOfAccess(String typeOfAccess) {
        this.typeOfAccess = typeOfAccess;
    }

}
