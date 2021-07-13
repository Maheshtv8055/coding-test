package com.itunesdata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itunesdata.model.ResArtist
import com.itunesdata.network.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    var artistList = MutableLiveData<ResArtist>()

    var isLoading = MutableLiveData<Boolean>()

    fun getArtistDetails(artistName: String) {
        isLoading.value = true
        NetworkModule.getApiClient().getArtistDetails(artistName).enqueue(object :
            Callback<ResArtist> {
            override fun onResponse(call: Call<ResArtist>, response: Response<ResArtist>) {
                isLoading.value = false
                artistList.value = response.body()
            }

            override fun onFailure(call: Call<ResArtist>, t: Throwable) {
                isLoading.value = false
                Log.e("TAG", "onFailure: ${t.localizedMessage}")
            }
        })

    }

}