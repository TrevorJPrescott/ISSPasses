package com.trevorpc.internationalspacestationpasses.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullResponse {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("request")
    @Expose
    public Request request;
    @SerializedName("response")
    @Expose
    public List<Response> response = null;



}