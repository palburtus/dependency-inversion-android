package com.example.dependencyinversion.callbacks

import com.example.dependencyinversion.model.NewsArticle

interface INewsCallback {

    fun onSuccess(articles: List<NewsArticle>)
    fun onError(message: String)
}