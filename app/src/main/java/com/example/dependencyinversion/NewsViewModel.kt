package com.example.dependencyinversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    val newsItems: MutableLiveData<List<String>> = MutableLiveData();

    fun fetchData(){

        val titles = mutableListOf<String>()
        titles.add("Title One")
        titles.add("Title Two")
        titles.add("Title Three")

        newsItems.postValue(titles);
    }
}