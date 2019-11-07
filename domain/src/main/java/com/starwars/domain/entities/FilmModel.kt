package com.starwars.domain.entities

import java.io.Serializable

data class FilmModel(var title:String?, var episode_id:Int?, var opening_crawl:String?, var release_date:String?) :Serializable
