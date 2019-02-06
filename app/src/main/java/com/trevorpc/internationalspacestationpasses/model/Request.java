package com.trevorpc.internationalspacestationpasses.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("altitude")
    @Expose
    public Integer altitude;
    @SerializedName("datetime")
    @Expose
    public Integer datetime;
    @SerializedName("latitude")
    @Expose
    public Float latitude;
    @SerializedName("longitude")
    @Expose
    public Float longitude;
    @SerializedName("passes")
    @Expose
    public Integer passes;

}