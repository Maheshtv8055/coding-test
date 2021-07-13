package com.itunesdata

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.itunesdata.adapter.AdapterArtistList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        addObserver()
        btnSearch.setOnClickListener {
            if (edtArtistName.text.isEmpty()) {
                edtArtistName.error = "Please enter artist"
                edtArtistName.requestFocus()
                return@setOnClickListener
            }
            getArtistList(edtArtistName.text.toString())
            edtArtistName.hideKeyboard()
        }
    }

    private fun addObserver() {
        viewModel.artistList.observe(this, { resArtist ->
            recyclerView.adapter = AdapterArtistList(resArtist)
        })

        viewModel.isLoading.observe(this, { isLoading ->
            if (isLoading) {
                rlProgressBar.visibility = View.VISIBLE
            } else {
                rlProgressBar.visibility = View.GONE
            }
        })
    }

    private fun getArtistList(artistName: String) {
        viewModel.getArtistDetails(artistName)
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}