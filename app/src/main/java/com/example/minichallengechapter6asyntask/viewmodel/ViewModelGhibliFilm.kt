package com.example.minichallengechapter6asyntask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minichallengechapter6asyntask.model.GetAllGhibliFilmResponseItem
import com.example.minichallengechapter6asyntask.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelGhibliFilm : ViewModel() {
    private var liveDataGhibliFilm : MutableLiveData<List<GetAllGhibliFilmResponseItem>> = MutableLiveData()
    fun getLiveGhibliFilmObserver() : MutableLiveData<List<GetAllGhibliFilmResponseItem>>{
        return liveDataGhibliFilm
    }
    fun makeApiGhibliFilm(){
        ApiClient.instance.getAllFilms()
            .enqueue(object : Callback<List<GetAllGhibliFilmResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetAllGhibliFilmResponseItem>>,
                    response: Response<List<GetAllGhibliFilmResponseItem>>
                ) {
                    if(response.isSuccessful){
                        liveDataGhibliFilm.postValue(response.body())
                    }else{
                        liveDataGhibliFilm.postValue(null)
                    }
                }

                override fun onFailure(
                    call: Call<List<GetAllGhibliFilmResponseItem>>,
                    t: Throwable
                ) {
                    liveDataGhibliFilm.postValue(null)
                }

            })
    }
}