package com.demo.twitter.twitterapp.data.repository

import com.demo.twitter.twitterapp.data.retrofit.RetrofitService
import com.demo.twitter.twitterapp.model.Tweet
import io.realm.Realm
import io.realm.kotlin.where

class FeedRepository {
    val retrofitService = RetrofitService()
    suspend fun getUserFeed(): List<Tweet>? {
        return retrofitService.getUserFeed()
    }


    fun saveUserFeed(tweets: List<Tweet>?) {
        val realm = Realm.getDefaultInstance()

        realm?.executeTransaction { realm ->
            realm.deleteAll()
            realm.copyToRealm(tweets)
        }
    }

    fun getLocalFeed(): List<Tweet>? {
        val realm = Realm.getDefaultInstance()
        var tweets = realm?.where<Tweet>()?.findAll()
        return tweets
    }
}