package com.demo.twitter.twitterapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Tweet(
    var created_at: String?=null, @field:PrimaryKey var id: Long? = null,
    var text: String? = null,
    var user: User? = null,
    var retweet_count: Int = 0,
    var favorite_count: Int = 0,
    var favorited: Boolean = false,
    var retweeted: Boolean = false
) : RealmObject()