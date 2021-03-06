package com.example.dependencyinversion.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinversion.api.INewsClient
import com.example.dependencyinversion.callbacks.INewsCallback
import com.example.dependencyinversion.model.NewsArticle

class NewsViewModel(private val newsClient: INewsClient) : ViewModel() {

    val newsItems: MutableLiveData<List<NewsArticle>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun fetchData(){

        errorMessage.postValue("")

        this.newsClient.get(object: INewsCallback {
            override fun onSuccess(articles: List<NewsArticle>) {
                newsItems.postValue(articles)
            }

            override fun onError(message: String) {
                errorMessage.postValue(message)
            }
        })
    }

}