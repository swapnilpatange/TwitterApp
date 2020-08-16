package com.demo.twitter.twitterapp.view.activity

import android.os.Bundle
import android.view.View
import com.twitter.sdk.android.core.*
import kotlinx.android.synthetic.main.activity_twitter_login.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import android.content.Intent
import android.os.Handler
import android.util.Log
import com.demo.twitter.twitterapp.R

class TwitterLoginActivity : BaseActivity(), View.OnClickListener {

    private var client: TwitterAuthClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Twitter.initialize(this)
        setContentView(R.layout.activity_twitter_login)
        initObject()
        defaultLoginTwitter()
    }

    private fun defaultLoginTwitter() {
        if (getTwitterSession() != null) {
            btn_twitter_login.text = "Logged in as " + getUserLoggedIn()
            gotoFeed()
        } else {
            setListener()
        }
    }

    private fun fetchTwitterEmail(twitterSession: TwitterSession?) {
        client?.requestEmail(twitterSession, object : Callback<String>() {
            override fun success(result: Result<String>?) {
                saveLogin(twitterSession?.userName)
                btn_twitter_login.text = "Logged in as " + twitterSession?.userName
                gotoFeed()
                Log.d(javaClass.simpleName, "")
            }

            override fun failure(exception: TwitterException?) {
                Log.d(javaClass.simpleName, "")
            }

        })
    }

    private fun gotoFeed() {
        Handler().postDelayed(Runnable {
            val intent = Intent(this@TwitterLoginActivity, TwitterFeedActivity::class.java)
            startActivity(intent)
            finish()
        }, 800)
    }

    private fun initObject() {
        client = TwitterAuthClient()
    }

    private fun setListener() {
        btn_twitter_login.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_twitter_login -> {
                client?.authorize(this, object : Callback<TwitterSession>() {
                    override fun success(result: Result<TwitterSession>?) {
                        val twitterSession = result?.data
                        fetchTwitterEmail(twitterSession)
                    }

                    override fun failure(exception: TwitterException?) {
                        Log.d(javaClass.simpleName, "")
                    }

                })
            }
        }
    }

    private fun getTwitterSession(): TwitterSession? {
        return TwitterCore.getInstance()?.sessionManager.let { it?.activeSession } ?: null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the twitterAuthClient.
        if (client != null)

            client?.onActivityResult(requestCode, resultCode, data)
        // default_twitter_button.onActivityResult(requestCode, resultCode, data)
    }

}
