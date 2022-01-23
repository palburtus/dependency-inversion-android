package com.example.dependencyinversion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinversion.api.INewsClient
import com.example.dependencyinversion.model.NewsArticle
import com.example.dependencyinversion.model.NewsMetaData

class NewsViewModel(private val newsClient: INewsClient) : ViewModel() {

    val newsItems: MutableLiveData<List<NewsArticle>> = MutableLiveData();

    fun fetchData(){

        val articles = this.newsClient.get();

        newsItems.postValue(articles);
    }
}