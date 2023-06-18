package com.example.geniusgym.business

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.example.geniusgym.R
import com.example.geniusgym.databinding.ActivityBuBinding

class BuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuBinding
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar_bytiv)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.bu_menu_title)
        toolbar.setTitleTextColor(Color.BLACK)

//        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_keyboard_backspace_24)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.bu_option_menu_filter_search, menu)
//
//        val searchItem = menu?.findItem(R.id.toolbar_search)
//        searchView = searchItem?.actionView as SearchView
//
//        searchView.setOnQueryTextFocusChangeListener(object : SearchView.OnQueryTextListener){
//
//        }
//    }


}