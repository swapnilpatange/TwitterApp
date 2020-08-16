package com.demo.twitter.twitterapp.view.activity

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    fun isNetworkConnected(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

    fun getUserLoggedIn(): String {
        val sharedPreferences = getSharedPreferences("loginPref", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "")
    }

    fun saveLogin(username: String?) {
        val sharedPreferences = getSharedPreferences("loginPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }
}