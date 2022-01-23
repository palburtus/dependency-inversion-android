package com.example.dependencyinversion.api

import com.example.dependencyinversion.model.NewsArticle

interface INewsClient {
    fun get(): List<NewsArticle>
}



//https://img.staging.medscape.com/pi/iphone/testassets/sampleData.json