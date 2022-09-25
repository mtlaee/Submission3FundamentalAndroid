package com.example.submission2aplikasigithubtaufikakbar.Api

import com.example.submission2aplikasigithubtaufikakbar.Response.DataUser
import com.example.submission2aplikasigithubtaufikakbar.Response.DetailUserResponse
import com.example.submission2aplikasigithubtaufikakbar.Response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization:token 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")
    @GET("search/users")
    fun searchuser(
        @Query("q") query: String
    ): Call<ResponseUser>


    @Headers("Authorization:token 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")
    @GET("users/{username}")
    fun detailuser(
        @Path("username") username: String
    ): Call<DetailUserResponse>


    @Headers("Authorization:token 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")
    @GET("users/{username}/followers")
    fun getfollowers(
        @Path("username") username: String
    ): Call<ArrayList<DataUser>>

    @Headers("Authorization:token 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")
    @GET("users/{username}/following")
    fun getfollowing(
        @Path("username") username: String
    ): Call<ArrayList<DataUser>>
}