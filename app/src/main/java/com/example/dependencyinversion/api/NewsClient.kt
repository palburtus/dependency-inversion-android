package com.example.dependencyinversion.api

import com.example.dependencyinversion.callbacks.INewsCallback
import com.example.dependencyinversion.model.NewsArticle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsClient : INewsClient {

    @GET("pi/iphone/testassets/sampleData.json")
    fun getArticles(): Call<List<NewsArticle>>

    companion object {
        val BASE_URL = "https://img.staging.medscape.com/";

        fun create() : NewsClient {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(NewsClient::class.java)
        }
    }

    override fun get(callback: INewsCallback) {

        val api = create().getArticles();

        api.enqueue(object : Callback<List<NewsArticle>> {
            override fun onResponse(
                call: Call<List<NewsArticle>>,
                response: Response<List<NewsArticle>>
            ) {
                response.body()?.let { callback.onSuccess(it) }
            }

            override fun onFailure(call: Call<List<NewsArticle>>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }
        })
    }

}