package com.bellano.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bellano.myapplication.databinding.MainActivityBinding
import com.bellano.themoviedblibrary.network.ApiHelper


class MainActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
    binding.recyclerView.layoutManager = GridLayoutManager (this, 1) // LinearLayoutManager(this)
    val films  = ApiHelper.getPopularMovies()?.results
    val imageUrl = ApiHelper.getImageBaseUrl()
    if (films != null) {
      val adapter = MyAdapter(films)
      binding.recyclerView.adapter = adapter
    }



    val searchView = findViewById<SearchView>(R.id.search)
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextChange(newText: String): Boolean {
        val movies = ApiHelper.searchMovie(newText)
        if (movies != null) {
          val adapter = MyAdapter(movies.results)
          binding.recyclerView.adapter = adapter
        }
        return false
      }
      override fun onQueryTextSubmit(query: String): Boolean {
        return false
      }
    })

  }

}