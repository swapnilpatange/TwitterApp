package com.demo.twitter.twitterapp.data.retrofit

import android.util.Log
import com.demo.twitter.twitterapp.model.Tweet
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {


    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {
            Log.e("retrofit", "create")
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.twitter.com/1.1/statuses/")
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

    suspend fun getUserFeed(): List<Tweet>? {
        val retrofitCall = create().getUserFeed()
        if (retrofitCall.isSuccessful) {
            return retrofitCall?.body()
        }
        return null
    }
}
