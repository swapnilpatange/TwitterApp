package com.demo.twitter.twitterapp.application

import android.app.Application
import android.util.Log
import com.demo.twitter.twitterapp.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import io.realm.Realm
import io.realm.RealmConfiguration


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))//enable logging when app is in debug mode
            .twitterAuthConfig(
                TwitterAuthConfig(
                    resources.getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                    resources.getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)
                )
            )//pass the created app Consumer KEY and Secret also called API Key and Secret
            .debug(true)//enable debug mode
            .build()

        //finally initialize twitter with created configs
        Twitter.initialize(config)

        //Fresco library initiated
        Fresco.initialize(this)

        //Realm library initiated
        Realm.init(getApplicationContext())


        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfig)
    }
}