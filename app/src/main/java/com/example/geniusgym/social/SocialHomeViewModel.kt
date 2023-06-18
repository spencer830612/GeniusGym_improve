package com.example.geniusgym.social

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.R

class SocialHomeViewModel : ViewModel() {
    private val articles: MutableLiveData<List<Post>> = MutableLiveData()

    init {
        loadArticles()
    }

    fun getArticles(): LiveData<List<Post>> {
        return articles
    }

    private fun loadArticles() {
        val articlesData: List<Post> = listOf(
            Post(1,
                R.drawable.eren_yeager,
                "Eren Yeager",
                "Post 1 content",
                R.drawable.eren_imege1,
                10,
                5,
                "2023-06-07"),
            Post(2,
                R.drawable.walter_white,
                "Walter White",
                "Turkey's President Recep Tayyip Erdogan has spoken to his counterparts in both Ukraine and Russia, and called for a joint investigation to establish the cause of the breach of the Kakhovka dam. Tens of thousands of people have been left at risk of flooding as a result of the incident.",
                R.drawable.breakingbad,
                15,
                3,
                "2023-06-06"),
            Post(3,
                R.drawable.saitama,
                "Saitama",
                "Post 3 content",
                null,
                20,
                7,
                "2023-06-05")
        )
        articles.value = articlesData
    }
}