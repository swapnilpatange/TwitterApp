package com.demo.twitter.twitterapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.twitter.twitterapp.data.repository.FeedRepository
import com.demo.twitter.twitterapp.model.Tweet
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val mutableMoviesLiveData: MutableLiveData<List<Tweet>> = MutableLiveData()

    val feedRepository = FeedRepository()
    fun getUserFeed(): MutableLiveData<List<Tweet>> {

        viewModelScope.launch {
            val apiResponse = feedRepository?.getUserFeed()
            mutableMoviesLiveData?.value = apiResponse
            feedRepository.saveUserFeed(apiResponse)
        }
        return mutableMoviesLiveData
    }

    fun getLocalFeed(): List<Tweet>? {
        return feedRepository.getLocalFeed()
    }

}