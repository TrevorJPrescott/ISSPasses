package com.trevorpc.internationalspacestationpasses.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("duration")
    @Expose
    public Integer duration;
    @SerializedName("risetime")
    @Expose
    public Integer risetime;


}