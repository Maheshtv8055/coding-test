package com.itunesdata

import com.itunesdata.network.NetworkModule
import junit.framework.Assert.assertTrue
import org.junit.Test

class MainActivityViewModelTest {

    @Test
    fun getArtistTest() {
        val response = NetworkModule.getApiClient().getArtistDetails("arijit").execute()
        assertTrue(response.isSuccessful)
    }
}