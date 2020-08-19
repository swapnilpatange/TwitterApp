package com.demo.twitter.twitterapp.data.retrofit

import com.demo.twitter.twitterapp.model.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @Headers("authorization: OAuth oauth_consumer_key=\"qFQExz5t6rMBLVevvvQTxbEOO\",oauth_token=\"2560733537-ceA2rZLC0o5vlwnpgLKOCVbYZDEcr1MLuMe0NTa\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1597846445\",oauth_nonce=\"ObOoiT\",oauth_version=\"1.0\",oauth_signature=\"Q1RQcPue8c8yrAL2wGc9R4B1AQM%3D\"")
    @GET("home_timeline.json")
    suspend fun getUserFeed() : Response<List<Tweet>>
}