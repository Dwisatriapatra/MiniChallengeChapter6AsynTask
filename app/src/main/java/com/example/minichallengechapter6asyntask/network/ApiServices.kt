package com.example.minichallengechapter6asyntask.network

import com.example.minichallengechapter6asyntask.model.GetAllGhibliFilmResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("films")
    fun getAllFilms() : Call<List<GetAllGhibliFilmResponseItem>>
}