package com.example.jamal.mobilecodinchallengejmarouf.rest;

import com.example.jamal.mobilecodinchallengejmarouf.model.GithubApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("repositories")
    Call<GithubApiResponse> getRepositories(@Query("q") String query, @Query("sort") String sort, @Query("order") String order, @Query("page") int page);

}
