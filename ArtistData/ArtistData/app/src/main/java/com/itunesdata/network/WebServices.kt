package com.itunesdata.network

import com.itunesdata.model.ResArtist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    //?term={any ArtistName}
    @GET("/search")
    fun getArtistDetails(@Query("term") artistName: String): Call<ResArtist>


}