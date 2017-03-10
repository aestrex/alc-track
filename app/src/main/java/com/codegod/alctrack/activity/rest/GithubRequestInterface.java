package com.codegod.alctrack.activity.rest;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alamzdayveed on 10/03/2017.
 */

public interface GithubRequestInterface {
    @GET("search/users?q=type:user+location:Lagos+language:java&per_page=100")
    Call<GithubJSONResponse> getJSON();
}
