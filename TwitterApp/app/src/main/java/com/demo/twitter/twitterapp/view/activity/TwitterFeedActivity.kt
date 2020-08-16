package com.demo.twitter.twitterapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.twitter.twitterapp.viewmodel.FeedViewModel
import com.demo.twitter.twitterapp.R
import com.demo.twitter.twitterapp.model.Tweet
import com.demo.twitter.twitterapp.view.adapter.TwitterListAdapter
import com.twitter.sdk.android.core.TwitterCore
import kotlinx.android.synthetic.main.activity_twitter_feed.*

class TwitterFeedActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_feed)
        setSupportActionBar(toolbar)

        val feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        var tweets = feedViewModel.getLocalFeed()!!

        if (!isNetworkConnected() && tweets?.size > 0) {
            loadFeedList(tweets)
        } else if (!isNetworkConnected()) {
            no_result.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        } else {
            feedViewModel.getUserFeed().observe(this, Observer { t ->
                loadFeedList(t)
                Log.d(javaClass.simpleName, "")
            })
        }
    }

    private fun loadFeedList(tweets: List<Tweet>) {
        progressBar.visibility = View.GONE
        var twitterListAdapter = TwitterListAdapter(this)
        twitterListAdapter.tweets = tweets
        tweet_list.adapter = twitterListAdapter
        tweet_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_logout -> {
                logoutTwitter()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun logoutTwitter() {
        saveLogin("")
        CookieSyncManager.createInstance(this)
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeSessionCookie()
        TwitterCore.getInstance().getSessionManager().clearActiveSession()
        val intent = Intent(this, TwitterLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
