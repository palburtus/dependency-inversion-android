package com.example.dependencyinversion.mocks

import com.example.dependencyinversion.api.INewsClient
import com.example.dependencyinversion.callbacks.INewsCallback
import com.example.dependencyinversion.model.NewsArticle
import com.example.dependencyinversion.model.NewsMetaData

class MockNewsClient : INewsClient {

    override fun get(callback: INewsCallback) {

        val articles = mutableListOf<NewsArticle>()

        articles.add(NewsArticle("Title One", NewsMetaData("describe 1", "http://www.image.com/1", "http://test.com/article1")))
        articles.add(NewsArticle("Title Two", NewsMetaData("describe 2", "http://www.image.com/2", "http://test.com/article2")))
        articles.add(NewsArticle("Title Three", NewsMetaData("describe 3", "http://www.image.com/3", "http://test.com/article3")))

        callback.onSuccess(articles);
    }
}