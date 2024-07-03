package com.example.appservice

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET
    suspend fun getListOfCharacters(@Url url: String): Response<DataResponse>
}