package com.demo.twitter.twitterapp.view.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.demo.twitter.twitterapp.R
import com.demo.twitter.twitterapp.model.Tweet
import kotlinx.android.synthetic.main.tweet_list_item.view.*

class TwitterListAdapter(var activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var tweets: List<Tweet>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet_list_item, null)
        return TwitterListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tweets?.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TwitterListViewHolder) {
            holder.bind(tweets.get(position), activity)
        }
    }

    internal class TwitterListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tweet: Tweet, activity: Activity) {
            itemView?.profile_img.setImageURI(Uri.parse(tweet.user?.profile_image_url_https))
            itemView?.name.text = tweet.user?.name
            itemView?.username.text = "@" + tweet.user?.screen_name
            itemView?.time.text = tweet.created_at
            itemView?.tweet_text.text = tweet.text
            itemView?.txt_retweet.text = tweet.retweet_count.toString()
            itemView?.txt_love.text = tweet.favorite_count.toString()
            if (tweet.user!!.verified)
                itemView?.img_Verified.visibility = View.VISIBLE
            else
                itemView?.img_Verified.visibility = View.GONE

            if (tweet.retweeted) {
                itemView?.img_retweet.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary))
            } else {
                itemView?.img_retweet.setColorFilter(ContextCompat.getColor(activity, R.color.warm_grey))
            }

            if (tweet.favorited) {
                itemView?.img_love.setColorFilter(ContextCompat.getColor(activity, R.color.red))
            } else {
                itemView?.img_love.setColorFilter(ContextCompat.getColor(activity, R.color.warm_grey))
            }
        }
    }
}