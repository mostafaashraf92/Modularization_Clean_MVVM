package com.starwars.core

class UrlParseHelper {

    fun getUrlFromString(url:String?): String?
    {
        return url?.substring(url.lastIndexOf("api/") + 4)
    }
}