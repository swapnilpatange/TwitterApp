package com.demo.twitter.twitterapp.model

import io.realm.RealmObject

open class User(var name:String?=null,var screen_name:String?=null,var profile_image_url_https:String?=null,var verified:Boolean=false):RealmObject()